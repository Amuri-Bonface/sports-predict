package com.mysportpesa.surebetsclient;

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
import android.widget.Button;
import android.widget.ImageView;

import static com.activeandroid.Cache.getContext;

public class PremiumTips extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium_tips);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button dailybtn=findViewById(R.id.btndaily);
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

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void premium(){
        //COPY phone number to clipboard
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", "0717264871");
        clipboard.setPrimaryClip(clip);

        Snackbar.make(findViewById(android.R.id.content), "0717264871 has been copied", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.GREEN)
                .setDuration(5000)
                .setAction("Click & Use Send Money", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PackageManager manager = getPackageManager();
                        Intent intent =manager.getLaunchIntentForPackage("com.android.stk");
                        if (intent != null)
                            startActivity(intent);

                    }
                })
                .show();

    }

}
