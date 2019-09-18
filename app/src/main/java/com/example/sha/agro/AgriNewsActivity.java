package com.example.sha.agro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class AgriNewsActivity extends AppCompatActivity {


    private Toolbar mToolBar1;
    private ViewPager myViewPager1;
    private TabLayout myTabLayout1;
    private AgriNewsTAA myTabAccessorAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agri_news);




        myViewPager1 =(ViewPager) findViewById(R.id.main_tabs_pager1);
        myTabAccessorAdapter1 = new AgriNewsTAA(getSupportFragmentManager());
        myViewPager1.setAdapter(myTabAccessorAdapter1);

        myTabLayout1 = (TabLayout) findViewById(R.id.main_tabs1);
        myTabLayout1.setupWithViewPager(myViewPager1);




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

}
