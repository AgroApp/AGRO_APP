package com.example.sha.agro;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
}
