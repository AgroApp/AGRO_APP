package com.example.sha.agro;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import static com.example.sha.agro.LocaleManager.setNewLocale;

public class Main2Activity extends AppCompatActivity
{


    private DatabaseReference mDatabase;
    private String My_Lang = "en";
    private String lang = My_Lang;
private ImageView langchange;
    private FirebaseAuth mAuth;

    GridLayout Maingrid;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        Maingrid =(GridLayout) findViewById(R.id.gridlayout);

        setSingleEvent(Maingrid);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        My_Lang = intent.getStringExtra("My_Lang");

           // My_Lang = bundle.getString("My_Lang");

        langchange = (ImageView) findViewById(R.id.logo);
        langchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }

            private void showChangeLanguageDialog() {
                final String[] listItems = {"தமிழ்", "English"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main2Activity.this);
                mBuilder.setTitle(getString(R.string.choose_lang));
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {

                            setLocale("ta");
                            recreate();

                        } else if (i == 1) {

                            setLocale("en");
                            recreate();

                        } else

                            setLocale("en");

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


    }


    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)
        {
            Intent intent = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setSingleEvent(GridLayout Maingrid) {
        int i;


    for (i = 0; i < Maingrid.getChildCount(); i++) {
        CardView cardView = (CardView) Maingrid.getChildAt(i);
        final int finalI = i;

        cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (finalI == 0) {
                    if(lang =="en") {
                        Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(),ShopsTamil.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                }
                else if (finalI == 1) {
                    if(lang =="en") {
                        Intent intent = new Intent(Main2Activity.this, AgriNewsActivity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                     else {
                        Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                    }
                } else if (finalI == 2) {
                    if(lang =="en") {
                        Intent intent = new Intent(Main2Activity.this, BankLoanActivity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                    }
                } else if (finalI == 3) {
                    if(lang =="en") {
                        Intent intent = new Intent(Main2Activity.this, HospitalsActivity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                     else  {
                        Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                    }
                } else if (finalI == 4) {
                    if(lang =="en") {
                        Intent intent = new Intent(Main2Activity.this, ResearchCentreActivity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                     else{
                        Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                    }
                } else if (finalI == 5) {
                    if(lang =="en") {
                        Intent intent = new Intent(Main2Activity.this, PharmacyActivity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                     else if(lang == "ta"){
                        Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                    }
                } else if (finalI == 6) {
                    if(lang =="en") {
                        Intent intent = new Intent(Main2Activity.this, HelplineActivity.class);
                        intent.putExtra("My_Lang", My_Lang);
                        startActivity(intent);
                    }
                     else {
                        Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Main2Activity.this, "the cardview " + finalI, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

        AskPermission();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivitymenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
                startActivity(profile);
                break;


            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent logout = new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
        }
        return true;

    }

    private void AskPermission(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString(My_Lang,  Locale.getDefault().getLanguage());
        setLocale(language);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString(My_Lang, lang);
        editor.apply();
    }

}
