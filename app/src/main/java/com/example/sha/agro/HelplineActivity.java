package com.example.sha.agro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class HelplineActivity extends AppCompatActivity {


    private ViewPager helpViewPager;
    private TabLayout helpTabLayout;
    private HelplineTAA helpTabAccessorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);


        helpViewPager =(ViewPager) findViewById(R.id.main_tabs_pager_H);
        helpTabAccessorAdapter = new HelplineTAA(getSupportFragmentManager());
        helpViewPager.setAdapter(helpTabAccessorAdapter);

        helpTabLayout = (TabLayout) findViewById(R.id.main_tabs_H);
        helpTabLayout.setupWithViewPager(helpViewPager);



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
            case R.id.home:
                Intent home = new Intent(this, Main2Activity.class);
                startActivity(home);
                break;

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
