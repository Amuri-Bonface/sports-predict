package com.mysportpesa.surebetsclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mysportpesa.surebetsclient.util.CheckInternetConnection;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MegaJackPotEditDb extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    String id=null;
    String leagueName1=null;
    String homeTeam1=null;
    String awayTeam1=null;
    String status1=null;
    String odds1=null;
    String prediction1=null;
    String dateTime1=null;
    String result1=null;
    Button myUpdateBt,updateAgain;
    private EditText leagueName,homeT,awayT,status,result,odds,prediction,dateTime;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mega_jackpot_populatedb);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//views
        leagueName=(EditText)findViewById(R.id.league);
        homeT=(EditText)findViewById(R.id.home);
        awayT=(EditText)findViewById(R.id.away);
        status=(EditText)findViewById(R.id.mystatus);
        result=(EditText)findViewById(R.id.myresult);
        odds=(EditText)findViewById(R.id.myodds);
        prediction=(EditText)findViewById(R.id.myprediction);
        dateTime=(EditText)findViewById(R.id.datetime);

        //Get data to edit
        Intent intent = getIntent();
         leagueName1= intent.getStringExtra("leagueName");
         homeTeam1= intent.getStringExtra("homeTeam");
         awayTeam1= intent.getStringExtra("awayTeam");
         dateTime1= intent.getStringExtra("dateTime");
         odds1= intent.getStringExtra("odds");
         prediction1= intent.getStringExtra("prediction");
         result1= intent.getStringExtra("result");
         status1= intent.getStringExtra("status");
        id= intent.getStringExtra("id");


        //display text
        leagueName.setText(leagueName1);
        homeT.setText(homeTeam1);
        awayT.setText(awayTeam1);
        dateTime.setText(dateTime1);
        odds.setText(odds1);
        prediction.setText(prediction1);
        result.setText(result1);
        status.setText(status1);

        myUpdateBt=(Button)findViewById(R.id.update);
        myUpdateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               poulateDb();
            }
        });

        updateAgain=(Button)findViewById(R.id.update_twice);
        updateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedB();
            }
        });
        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private  void poulateDb()
    {
        Map<String, Object> data = new HashMap<>();
        data.put("leagueName", leagueName.getText().toString());
        data.put("homeTeam", homeT.getText().toString());
        data.put("awayTeam", awayT.getText().toString());
        data.put("status", status.getText().toString());
        data.put("prediction", prediction.getText().toString());
        data.put("dateTime", dateTime.getText().toString());
        data.put("odds", odds.getText().toString());
        data.put("result", result.getText().toString());

        db.collection("megajackpot")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(), "DocumentSnapshot written with ID: " + documentReference.getId(),Toast.LENGTH_LONG).show();
                        //clear views
                        leagueName.setText("");
                        homeT.setText("");
                        awayT.setText("");
                        prediction.setText("");
                        odds.setText("");
                        status.setText("");
                        dateTime.setText("");
                        result.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error adding document"+ e,Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void updatedB(){
        DocumentReference updateref = db.collection("megajackpot").document(id);

        updateref
                .update("result",result.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       // Log.d(TAG, "DocumentSnapshot successfully updated!");
                }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                      //  Log.w(TAG, "Error updating document", e);
                    }
                });
        updateref
                .update("status", status.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //  Log.w(TAG, "Error updating document", e);
                    }
                });
        //update history
        addHistoryRecord();
    }

    private void addHistoryRecord()
    {
        Map<String, Object> data = new HashMap<>();
        data.put("leagueName", leagueName.getText().toString());
        data.put("homeTeam", homeT.getText().toString());
        data.put("awayTeam", awayT.getText().toString());
        data.put("status", status.getText().toString());
        data.put("prediction", prediction.getText().toString());
        data.put("dateTime", dateTime.getText().toString());
        data.put("odds", odds.getText().toString());
        data.put("result", result.getText().toString());

        db.collection("history")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(), "DocumentSnapshot written with ID: " + documentReference.getId(),Toast.LENGTH_LONG).show();
                        //clear views
                        leagueName.setText("");
                        homeT.setText("");
                        awayT.setText("");
                        prediction.setText("");
                        odds.setText("");
                        status.setText("");
                        dateTime.setText("");
                        result.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error adding document"+ e,Toast.LENGTH_LONG).show();
                    }
                });

    }

}
