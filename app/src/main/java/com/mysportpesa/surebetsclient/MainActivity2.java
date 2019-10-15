package com.mysportpesa.surebetsclient;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.mysportpesa.surebetsclient.ui.DoubleChance;
import com.mysportpesa.surebetsclient.ui.History;
import com.mysportpesa.surebetsclient.ui.LiveInPlay;
import com.mysportpesa.surebetsclient.ui.PremiumTips;
import com.mysportpesa.surebetsclient.ui.SureOdds;
import com.mysportpesa.surebetsclient.ui.UnderOver;
import com.mysportpesa.surebetsclient.util.CheckInternetConnection;
import com.mysportpesa.surebetsclient.util.UserSession;
import com.webianks.easy_feedback.EasyFeedback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity implements RewardedVideoAdListener {

        private SliderLayout sliderShow;
    private Drawer result;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;


    //to get user session data
    private UserSession session;
    private HashMap<String, String> user;
    private String name, email, photo, mobile;
    private FirebaseFirestore db;
    private InterstitialAd mInterstitialAd;
    private EditText phoneEdt;
    private String affiliate1=null;
    private String affiliate2=null;
    private String affiliate3=null;
    private String affiliate4=null;
    private String affiliate5=null;
    private String affiliate6=null;
    public String Url_API=null;
    private RewardedVideoAd mRewardedVideoAd;

    //,affiliate2,affiliate3,affiliate4,affiliate5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        TextView appname = findViewById(R.id.appname);
        appname.setTypeface(typeface);

        db = FirebaseFirestore.getInstance();
        phoneEdt=(EditText)findViewById(R.id.phone);

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);


        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

        //retrieve session values and display on listviews
        getValues();

        //Navigation Drawer with toolbar
        inflateNavDrawer();

        //ImageSLider
        inflateImageSlider();
        geturl2();

        if (session.getFirstTime()) {
            //tap target view
            tapview();
            session.setFirstTime(false);
        }

        MobileAds.initialize(MainActivity2.this,"cca-app-pub-7590760147512944~3231997997");
        loadAds1();
        loadAds2();
        loadAds3();
        loadFullscreenAd();

    }

    private void loadAds1(){
        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void loadAds2(){
        AdView mAdView;
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    private void loadAds3(){
        AdView mAdView;
        mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void loadFullscreenAd(){
        MobileAds.initialize(MainActivity2.this,"cca-app-pub-7590760147512944~3231997997");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7590760147512944/8727388323");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

            }

            @Override
            public void onAdLoaded() {

                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }
        });


    }


    private void tapview() {

            new TapTargetSequence(this)
                    .targets(
                            TapTarget.forView(findViewById(R.id.notifintro), "Offers", "Latest offers and free VIP coins!")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)                   // Whether to draw a drop shadow or not
                                    .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.first),
                            TapTarget.forView(findViewById(R.id.view_profile), "Twitter", "Check out our twitter for more bets !")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)                   // Whether to draw a drop shadow or not
                                    .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.third),
                            TapTarget.forView(findViewById(R.id.cart), "Contact Admin", "Here is Shortcut to contact admin !")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)
                                    .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.second),
                            TapTarget.forView(findViewById(R.id.visitingcards), "Categories", "Betting Tips are listed here! Scroll To view more. Submit phone No for free bets")
                                    .targetCircleColor(R.color.colorAccent)
                                    .titleTextColor(R.color.colorAccent)
                                    .titleTextSize(25)
                                    .descriptionTextSize(15)
                                    .descriptionTextColor(R.color.accent)
                                    .drawShadow(true)
                                    .cancelable(false)// Whether tapping outside the outer circle dismisses the view
                                    .tintTarget(true)
                                    .transparentTarget(true)
                                    .outerCircleColor(R.color.fourth))

                    .listener(new TapTargetSequence.Listener() {
                        // This listener will tell us when interesting(tm) events happen in regards
                        // to the sequence
                        @Override
                        public void onSequenceFinish() {
                            session.setFirstTime(false);
                            Toasty.success(MainActivity2.this, " You are ready to go !", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                        }

                        @Override
                        public void onSequenceCanceled(TapTarget lastTarget) {
                            // Boo
                        }
                    }).start();

    }


    private void getValues() {

        //create new session object by passing application context
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();

        //get User details if logged in
        user = session.getUserDetails();

        name = user.get(UserSession.KEY_NAME);
        email = user.get(UserSession.KEY_EMAIL);
        mobile = user.get(UserSession.KEY_MOBiLE);
        //photo = user.get(UserSession.KEY_PHOTO);
    }


    private void inflateImageSlider() {

        // Using Image Slider -----------------------------------------------------------------------
        sliderShow = findViewById(R.id.slider);

        //populating Image slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/FtyjIvcJA1e8xM3Pgsmt/image1/image1.jpg");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/FtyjIvcJA1e8xM3Pgsmt/image2/image2.jpg");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/FtyjIvcJA1e8xM3Pgsmt/image3/image3.jpg");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/FtyjIvcJA1e8xM3Pgsmt/image4/image4.png");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/FtyjIvcJA1e8xM3Pgsmt/image5/image5.jpg");
        sliderImages.add("https://storage.googleapis.com/sporting-4496c.appspot.com/sliderimages/FtyjIvcJA1e8xM3Pgsmt/image6/image6.jpg");

        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(s);
            sliderShow.addSlider(sliderView);

        }

        for(int i = 0; i<sliderImages.size();i ++) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            final int finalI = i;
            defaultSliderView.image(sliderImages.get(i))
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            switch (finalI){
                                case 0:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(affiliate1.toString())));
                                //Toast.makeText(MainActivity2.this,"Clicked Url"+affiliate1.toString(),Toast.LENGTH_LONG).show();
                           break;
                                case 1:
                                  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(affiliate2.toString())));
                                  //Toast.makeText(MainActivity2.this, "clicked image=" + sliderImages.get(finalI), Toast.LENGTH_SHORT).show();
                                  break;
                                case 2:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(affiliate3.toString())));
                                    //Toast.makeText(MainActivity2.this, "clicked image=" + sliderImages.get(finalI), Toast.LENGTH_SHORT).show();
                                    break;
                                    case 3:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(affiliate4.toString())));
                                    //Toast.makeText(MainActivity2.this, "clicked image=" + sliderImages.get(finalI), Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(affiliate5.toString())));
                                    //Toast.makeText(MainActivity2.this, "clicked image=" + sliderImages.get(finalI), Toast.LENGTH_SHORT).show();
                                    break;
                                case 5:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(affiliate6.toString())));
                                    //Toast.makeText(MainActivity2.this, "clicked image=" + sliderImages.get(finalI), Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(MainActivity2.this, "Default", Toast.LENGTH_LONG).show();
                                    break;


                        }}
                    });

            sliderShow.addSlider(defaultSliderView);
        }

        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }


    private void inflateNavDrawer() {

       //set Custom toolbar to activity -----------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the AccountHeader ----------------------------------------------------------------

        //Profile Making
        IProfile profile = new ProfileDrawerItem()
                .withName(name)
                .withEmail(email);
//                .withIcon(photo);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.gradient_background)
                .addProfiles(profile)
                .withCompactStyle(true)
                .build();

        //Adding nav drawer items ------------------------------------------------------------------
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.home).withIcon(R.drawable.home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.myprofile).withIcon(R.drawable.feedback);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.wishlist).withIcon(R.drawable.similar);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.cart).withIcon(R.drawable.notificon);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.logout).withIcon(R.drawable.logout);

        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(7).withName("Offers").withIcon(R.drawable.tag);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(8).withName(R.string.aboutapp).withIcon(R.drawable.credits);
        SecondaryDrawerItem item9 = new SecondaryDrawerItem().withIdentifier(9).withName(R.string.feedback).withIcon(R.drawable.feedback);
        SecondaryDrawerItem item10 = new SecondaryDrawerItem().withIdentifier(10).withName(R.string.helpcentre).withIcon(R.drawable.helpccenter);

        SecondaryDrawerItem item12 = new SecondaryDrawerItem().withIdentifier(12).withName("App Tour").withIcon(R.drawable.tour);
        SecondaryDrawerItem item13 = new SecondaryDrawerItem().withIdentifier(13).withName("Explore").withIcon(R.drawable.explore);


        //creating navbar and adding to the toolbar ------------------------------------------------
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withAccountHeader(headerResult)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        item1, item2, item3, item4, item5, new DividerDrawerItem(), item7, item8, item9, item10,new DividerDrawerItem(),item12,item13
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (position) {

                            case 1:
                                if (result != null && result.isDrawerOpen()) {
                                    result.closeDrawer();
                                }
                                break;
                            case 2:
                              // startActivity(new Intent(MainActivity2.this, Profile.class));
                                Snackbar.make(findViewById(android.R.id.content), "Please Subscribe to access Premium", Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.RED)
                                .setAction("Subscribe", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       startActivity(new Intent(MainActivity2.this, PremiumTips.class));
                                    }
                                })
                                .show();
                                break;
                            case 3:
                              startActivity(new Intent(MainActivity2.this, History.class));
                                break;
                            case 4:
                             //   startActivity(new Intent(MainActivity2.this, Cart.class));

                                Snackbar.make(findViewById(android.R.id.content), "Feature unavailable at the moment", Snackbar.LENGTH_LONG)
                                        .setActionTextColor(Color.RED)
                                        .setAction("Subscribe", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                              //  startActivity(new Intent(MainActivity2.this, PremiumTips.class));

                                            }
                                        })
                                        .dismiss();
                                break;
                            case 5:
                                session.logoutUser();
                                finish();
                                break;

                            case 7:

                                Snackbar.make(findViewById(android.R.id.content), "Currently no offers", Snackbar.LENGTH_LONG)
                                        .setActionTextColor(Color.RED)
                                        .setAction("Offers", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //  startActivity(new Intent(MainActivity2.this, PremiumTips.class));

                                            }
                                        })
                                        .dismiss();
                               // startActivity(new Intent(MainActivity2.this, NotificationActivity.class));
                                break;

                            case 8:
                               /* new LibsBuilder()
                                        .withFields(R.string.class.getFields())
                                        .withActivityTitle(getString(R.string.about_activity_title))
                                        .withAboutIconShown(true)
                                        .withAboutAppName(getString(R.string.app_name))
                                        *//*withAboutVersionShown(true)
                                        .withLicenseShown(true)
                                        .withAboutSpecial1(getString(R.string.domain))
                                        .withAboutSpecial1Description(getString(R.string.website))
                                        .withAboutSpecial2(getString(R.string.licence))
                                        .withAboutSpecial2Description(getString(R.string.licencedesc))
                                        .withAboutSpecial3(getString(R.string.changelog))
                                        .withAboutSpecial3Description(getString(R.string.changes))
                                        .withShowLoadingProgress(true)*//*
                                        .withAboutDescription(getString(R.string.about_activity_description))
                                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                                        .start(MainActivity2.this);*/
                                break;
                            case 9:
                                new EasyFeedback.Builder(MainActivity2.this)
                                        .withEmail("amuribonface@gmail.com.com")
                                        .withSystemInfo()
                                        .build()
                                        .start();
                                break;
                            case 10:
                               // startActivity(new Intent(MainActivity2.this, HelpCenter.class));
                                break;
                            case 12:
                                session.setFirstTimeLaunch(true);
                              //  startActivity(new Intent(MainActivity2.this, WelcomeActivity.class));
                                finish();
                                break;
                            case 13:
                                if (result != null && result.isDrawerOpen()) {
                                    result.closeDrawer();
                                }
                                tapview();
                                break;
                            default:
                                Toast.makeText(MainActivity2.this, "Default", Toast.LENGTH_LONG).show();

                        }

                        return true;
                    }
                })
                .build();

        //Setting crossfader drawer------------------------------------------------------------

        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));

        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = result.getMiniDrawer();

        //build the view for the MiniDrawer
        View view = miniResult.build(this);

        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));

        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });
    }


    @Override
    protected void onStop() {
       // loadFullscreenAd();
        sliderShow.stopAutoCycle();
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void viewProfile(View view) {
       // startActivity(new Intent(MainActivity2.this, Profile.class));
        String url = "https://twitter.com/LegitTipster";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void viewCart(View view) {
       // startActivity(new Intent(MainActivity2.this, Cart.class));
        String number="+254786420573";
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // loadFullscreenAd();
    }

    @Override
    protected void onResume() {

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
        sliderShow.startAutoCycle();
       // loadFullscreenAd();
        super.onResume();
    }


    public void Notifications(View view) {
       // startActivity(new Intent(MainActivity2.this, NotificationActivity.class));
    }

    public void underover(View view) {
        startActivity(new Intent(MainActivity2.this, UnderOver.class));
    }

    public void sureoddsactivity(View view) {
        startActivity(new Intent(MainActivity2.this, SureOdds.class));
    }


    public void goalgoalactivity(View view) {
   startActivity(new Intent(MainActivity2.this, com.mysportpesa.surebetsclient.ui.GoalGoal.class));
    }

    public void minijackpotactivity(View view) {

        startActivity(new Intent(MainActivity2.this, History.class));
    }

    public void megajackpotactivity(View view) {

        startActivity(new Intent(MainActivity2.this, MegaJackpot.class));
    }

    public void premiumactivity(View view) {

        startActivity(new Intent(MainActivity2.this, PremiumTips.class));
    }
    public void doublechanceactivity(View view) {

        startActivity(new Intent(MainActivity2.this, DoubleChance.class));
    }
    public void liveinplayactivity(View view) {

        startActivity(new Intent(MainActivity2.this, LiveInPlay.class));
    }


    public void submit_phoone(View view) {
        Map<String, Object> data = new HashMap<>();
        data.put("phone", phoneEdt.getText().toString());
        db.collection("users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Welcome: " + "We'll get in touch ",Toast.LENGTH_LONG).show();
                        //clear views
                        phoneEdt.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error"+ e,Toast.LENGTH_LONG).show();
                    }
                });
    }

 private void geturl2(){
     db.collection("affiliate")
             .get()
             .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<QuerySnapshot> task) {
                     if (task.isSuccessful()) {
                         for (DocumentSnapshot document : task.getResult()) {
                             affiliate1=document.getData().get("affiliate1").toString();
                             affiliate2=document.getData().get("affiliate2").toString();
                             affiliate3=document.getData().get("affiliate3").toString();
                             affiliate4=document.getData().get("affiliate4").toString();
                             affiliate5=document.getData().get("affiliate5").toString();
                             affiliate6=document.getData().get("affiliate6").toString();
                             Url_API=document.getData().get("API").toString();
                          // Toast.makeText(MainActivity2.this, document.getId() + " => " + affiliate1 ,Toast.LENGTH_LONG).show();
                         }
                     } else {
                       //  Log.d(TAG, "Error getting documents: ", task.getException());
                     }
                 }
             });
    }

    public void loadRewardedVideoAd(View view) {
        mRewardedVideoAd.loadAd("ca-app-pub-7590760147512944/3713635681",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, "Submit your phone number to receive VIP: " + reward.getType() + "  VIP coins: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}