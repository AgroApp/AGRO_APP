package com.example.sha.agro;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class PharmacyActivity extends AppCompatActivity {


    private ViewPager pharViewPager;
    private TabLayout pharTabLayout;
    private PharmacyTAA pharTabAccessorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);


        pharViewPager =(ViewPager) findViewById(R.id.main_tabs_pager_p);
        pharTabAccessorAdapter = new PharmacyTAA(getSupportFragmentManager());
        pharViewPager.setAdapter(pharTabAccessorAdapter);

        pharTabLayout = (TabLayout) findViewById(R.id.main_tabs_p);
        pharTabLayout.setupWithViewPager(pharViewPager);



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
