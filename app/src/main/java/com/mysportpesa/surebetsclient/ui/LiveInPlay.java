package com.mysportpesa.surebetsclient.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mysportpesa.surebetsclient.util.CheckInternetConnection;
import com.mysportpesa.surebetsclient.R;

public class LiveInPlay extends AppCompatActivity {

    private FirebaseFirestore db;
    public String livescore;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onStart() {
        super.onStart();

        getURL();
        //loadFullscreenAd();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_inplay);

        db = FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

            getURL();


        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
        loadAds();
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

    private void loadAds() {
        AdView mAdView;
        MobileAds.initialize(this,
                "cca-app-pub-7590760147512944~3231997997");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    private void getURL(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait Loading...");
        progressDialog.show();

        db.collection("affiliate")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                livescore=document.getData().get("livescore").toString();
                                // Toast.makeText(GoalGoal.this, document.getId() + " => " + Url_API.toString() ,Toast.LENGTH_LONG).show();

                                WebView myWebView = (WebView) findViewById(R.id.webview);
                                myWebView.setWebViewClient(new WebViewClient());

                                WebSettings webSettings = myWebView.getSettings();
                                webSettings.setJavaScriptEnabled(true);
                                webSettings.setLoadWithOverviewMode(true);
                                webSettings.setSupportZoom(true);
                                webSettings.getDisplayZoomControls();
                                myWebView.loadUrl(livescore);

                                progressDialog.dismiss();
                            }
                        } else {
                            //  Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

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