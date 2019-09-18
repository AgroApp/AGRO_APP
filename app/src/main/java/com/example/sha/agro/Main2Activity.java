package com.example.sha.agro;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            My_Lang = bundle.getString("My_Lang");
        }


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

        for ( i=0;i<Maingrid.getChildCount();i++)
        {
            CardView cardView = (CardView)Maingrid.getChildAt(i);
            final int finalI=i;

            cardView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    if(finalI ==0)
                    {
                       Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                       intent.putExtra("My_Lang",My_Lang);
                       startActivity(intent);
                    }

                    else if(finalI ==1)
                    {
                        Intent intent = new Intent(Main2Activity.this,AgriNewsActivity.class);
                        intent.putExtra("My_Lang",My_Lang);
                        startActivity(intent);
                    }
                    else if(finalI ==2)
                    {
                        Intent intent = new Intent(Main2Activity.this,BankLoanActivity.class);
                        intent.putExtra("My_Lang",My_Lang);
                        startActivity(intent);
                    }
                    else if(finalI ==3)
                    {
                        Intent intent = new Intent(Main2Activity.this,HospitalsActivity.class);
                        intent.putExtra("My_Lang",My_Lang);
                        startActivity(intent);
                    }
                    else if(finalI ==4)
                    {
                        Intent intent = new Intent(Main2Activity.this,ResearchCentreActivity.class);
                        intent.putExtra("My_Lang",My_Lang);
                        startActivity(intent);
                    }
                    else if(finalI ==5)
                    {
                        Intent intent = new Intent(Main2Activity.this,PharmacyActivity.class);
                        intent.putExtra("My_Lang",My_Lang);
                        startActivity(intent);
                    }
                    else if(finalI ==6)
                    {
                        Intent intent = new Intent(Main2Activity.this,HelplineActivity.class);
                        intent.putExtra("My_Lang",My_Lang);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(Main2Activity.this, "the cardview "+ finalI,Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
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
