package com.mysportpesa.surebetsclient.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mysportpesa.surebetsclient.util.CheckInternetConnection;
import com.mysportpesa.surebetsclient.R;

public class PremiumTips extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium_tips);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* Button dailybtn=findViewById(R.id.btndaily);
        dailybtn.setOnClickListener(v -> {
            //call premium function
            premium();
        });

        Button threeday=findViewById(R.id.btn3day);
        threeday.setOnClickListener(v -> {
            //call premium function
            premium();
        });

        Button weekly=findViewById(R.id.btnweekly);
        weekly.setOnClickListener(v -> {
            //call premium function
            premium();
        });


        Button monthly=findViewById(R.id.btnmonthly);
        monthly.setOnClickListener(v -> {
            //call premium function
            premium();
        });

        Button mega=findViewById(R.id.btnmega);
        mega.setOnClickListener(v -> {
            //call premium function
            premium();
        });

        Button mini=findViewById(R.id.btnmini);
        mini.setOnClickListener(v -> {
            //call premium function
            premium();
        });


       */
        ImageView mpesa=findViewById(R.id.mpesa);
        mpesa.setOnClickListener(v -> {
            //call premium function
            premium();
        });


       //contact admin via whatsapp
        ImageView newdoc= findViewById(R.id.newrecord);
        newdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number="+254786420573";
                String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        MobileAds.initialize(this,
                "cca-app-pub-7590760147512944~3231997997");
        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
        loadAds();
       // loadFullscreenAd();

    }

    @Override
    protected void onResume() {

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
      // loadFullscreenAd();
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loadFullscreenAd();
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        loadFullscreenAd();
        return true;
    }
    private void loadAds(){
        AdView mAdView;


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFullscreenAd();
    }

   private void premium(){
        //COPY phone number to clipboard
       /* android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", "+254717264871");
        clipboard.setPrimaryClip(clip);

        Snackbar.make(findViewById(android.R.id.content), "0717264871 copied", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.GREEN)
                .setDuration(6000)
                .setAction("Click & send Ksh. 700", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PackageManager manager = getPackageManager();
                        Intent intent =manager.getLaunchIntentForPackage("com.android.stk");
                        if (intent != null)
                            startActivity(intent);

                    }
                })
                .show();
*/
       String number="+254786420573";
       String url = "https://api.whatsapp.com/send?phone="+number;
       Intent i = new Intent(Intent.ACTION_VIEW);
       i.setData(Uri.parse(url));
       startActivity(i);
    }
 /*   private void paypal(){
      *//*  //COPY phone number to clipboard
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", "amuribonface@gmail.com");
        clipboard.setPrimaryClip(clip);*//*



        Snackbar.make(findViewById(android.R.id.content), "This will redirect you to paypal payment option", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.GREEN)
                .setDuration(6000)
                .setAction("Just pay $7 dollars", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=HL4WYTWBWVE72";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                    }
                })
                .show();
        String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=HL4WYTWBWVE72";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));


    }*/

    private void loadFullscreenAd(){

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


}
