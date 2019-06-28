package com.example.sha.agro;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class HospitalsActivity extends AppCompatActivity
{
    private ViewPager HospViewPager;
    private TabLayout HospTabLayout;
    private HospitalTAA HospTabAccessorAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);


        HospViewPager =(ViewPager) findViewById(R.id.main_tabs_pager_h);
        HospTabAccessorAdapter = new HospitalTAA(getSupportFragmentManager());
        HospViewPager.setAdapter(HospTabAccessorAdapter);

        HospTabLayout = (TabLayout) findViewById(R.id.main_tabs_h);
        HospTabLayout.setupWithViewPager(HospViewPager);






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
                Intent profile = new Intent(this, Main2Activity.class);
                startActivity(profile);
                break;

            case R.id.set:
                Intent settings = new Intent(this, Main2Activity.class);
                startActivity(settings);
                break;

            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent logout = new Intent(this, MainActivityPhoneAuth.class);
                startActivity(logout);
                break;
        }
        return true;

    }

}
