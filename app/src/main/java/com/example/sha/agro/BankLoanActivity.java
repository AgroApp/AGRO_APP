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

import java.util.Locale;

public class BankLoanActivity extends AppCompatActivity {


    private ViewPager banViewPager;
    private TabLayout banTabLayout;
    private BankloanTAA banTabAccessorAdapter;
    public String My_Lang= "en";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_loan);
        Bundle bundle = getIntent().getExtras();
        My_Lang = bundle.getString("My_Lang");

        Bundle arguments = new Bundle();
        arguments.putSerializable("My_Lang",My_Lang);



        banViewPager =(ViewPager) findViewById(R.id.main_tabs_pager_bl);
        banTabAccessorAdapter = new BankloanTAA(getSupportFragmentManager(), arguments);
        banViewPager.setAdapter(banTabAccessorAdapter);

        banTabLayout = (TabLayout) findViewById(R.id.main_tabs_bl);
        banTabLayout.setupWithViewPager(banViewPager);



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
