package com.mysportpesa.surebetsclient.ui;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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
import com.mysportpesa.surebetsclient.adapter.MyAdapter;
import com.mysportpesa.surebetsclient.model.GlobalModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UnderOver extends AppCompatActivity {

    private List<GlobalModel> itemList;
    private RecyclerView.Adapter adapter;
    private InterstitialAd mInterstitialAd;
    private FirebaseFirestore db;
    public String API = "https://apiv2.apifootball.com/?action=get_predictions&from=2019-10-01&to=2019-10-13&APIkey=17d65c65f706df2515f3c02c61c1a1f48343a396112e0136ba8a9f0eb72d768f";
    public String Url_API;

    @Override
    protected void onStart() {
        super.onStart();
        //loadFullscreenAd();
        getURL();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mList = findViewById(R.id.friend_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = FirebaseFirestore.getInstance();


        itemList = new ArrayList<>();
        adapter = new MyAdapter(getApplicationContext(),itemList);

        //Objects.requireNonNull(mList.getLayoutManager()).scrollToPosition(0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);
        //check Internet Connection

        new CheckInternetConnection(this).checkConnection();

        getURL();
        MobileAds.initialize(UnderOver.this,
                "cca-app-pub-7590760147512944~3231997997");

        loadAds();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      //  loadFullscreenAd();
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
       // loadFullscreenAd();
        return true;
    }
    private void loadAds() {
        AdView mAdView;


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url_API, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        GlobalModel globalModel = new GlobalModel();
                        globalModel.setMatch_id(jsonObject.getString("match_id"));
                        globalModel.setCountry_name(jsonObject.getString("country_name"));
                        globalModel.setLeague_name(jsonObject.getString("league_name"));

                        globalModel.setMatch_date(jsonObject.getString("match_date"));
                        globalModel.setMatch_time(jsonObject.getString("match_time"));
                        globalModel.setMatch_hometeam_name(jsonObject.getString("match_hometeam_name"));
                        globalModel.setMatch_awayteam_name(jsonObject.getString("match_awayteam_name"));
                        globalModel.setMatch_status(jsonObject.getString("match_status"));
                        globalModel.setMatch_hometeam_score(jsonObject.getString("match_hometeam_score"));
                        globalModel.setMatch_awayteam_score(jsonObject.getString("match_awayteam_score"));
                        globalModel.setProb_HW(jsonObject.getString("prob_HW"));
                        globalModel.setProb_AW(jsonObject.getString("prob_AW"));
                        globalModel.setProb_D(jsonObject.getString("prob_D"));
                        globalModel.setProb_HW_D(jsonObject.getString("prob_HW_D"));
                        globalModel.setProb_AW_D(jsonObject.getString("prob_AW_D"));
                        globalModel.setProb_HW_AW(jsonObject.getString("prob_HW_AW"));
                        globalModel.setProb_btts(jsonObject.getString("prob_bts"));
                        globalModel.setProb_otts(jsonObject.getString("prob_ots"));
                        globalModel.setProb_ov25(jsonObject.getString("prob_O"));
                        globalModel.setProb_u25(jsonObject.getString("prob_U"));
                        globalModel.setProb_o_1(jsonObject.getString("prob_O_1"));
                        globalModel.setProb_u_1(jsonObject.getString("prob_U_1"));
                        globalModel.setProb_o_3(jsonObject.getString("prob_O_3"));
                        globalModel.setProb_u_3(jsonObject.getString("prob_U_3"));

                        itemList.add(globalModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                       // Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
               // Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void getURL(){
        db.collection("affiliate")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Url_API=document.getData().get("APIUNDER").toString();
                               // Toast.makeText(GoalGoal.this, document.getId() + " => " + Url_API.toString() ,Toast.LENGTH_LONG).show();
                                getData();

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