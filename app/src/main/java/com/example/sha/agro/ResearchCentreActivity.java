package com.example.sha.agro;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class ResearchCentreActivity extends AppCompatActivity {


    private ViewPager resViewPager;
    private TabLayout resTabLayout;
    private ResearchCentresTAA resTabAccessorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_centre);


        resViewPager =(ViewPager) findViewById(R.id.main_tabs_pager_rc);
        resTabAccessorAdapter = new ResearchCentresTAA(getSupportFragmentManager());
        resViewPager.setAdapter(resTabAccessorAdapter);

        resTabLayout = (TabLayout) findViewById(R.id.main_tabs_rc);
        resTabLayout.setupWithViewPager(resViewPager);





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
