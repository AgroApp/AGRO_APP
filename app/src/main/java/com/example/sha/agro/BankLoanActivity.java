package com.example.sha.agro;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class BankLoanActivity extends AppCompatActivity {

    private ViewPager banViewPager;
    private TabLayout banTabLayout;
    private BankloanTAA banTabAccessorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_loan);



        banViewPager =(ViewPager) findViewById(R.id.main_tabs_pager_bl);
        banTabAccessorAdapter = new BankloanTAA(getSupportFragmentManager());
        banViewPager.setAdapter(banTabAccessorAdapter);

        banTabLayout = (TabLayout) findViewById(R.id.main_tabs_bl);
        banTabLayout.setupWithViewPager(banViewPager);



    }
}
