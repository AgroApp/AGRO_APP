package com.example.sha.agro;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

public class Main3Activity extends AppCompatActivity
{
    private Toolbar mToolBar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabAccessorAdapter myTabAccessorAdapter;
    private BottomNavigationView Navigation;
    private FertilizersFragment fertilizersFragment;
    private PesticideFragment pesticideFragment;
    private EquipmentsFragment equipmentsFragment;
    public String lang= "eng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //  Navigation = findViewById(R.id.navigationbar);
       /* mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("agro");*/
        Bundle bundle = getIntent().getExtras();
        lang = bundle.getString("lang");

       //Bundle arguments = new Bundle();
        bundle.putSerializable("lang",lang);

        myViewPager = (ViewPager) findViewById(R.id.main_tabs_pager);
        myTabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager(), bundle);
        myViewPager.setAdapter(myTabAccessorAdapter);

        myTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);

       //// Bundle  = getIntent().getExtras();
       // lang = bundle.getString("lang");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
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

     /*   fertilizersFragment = new FertilizersFragment();
        pesticideFragment = new PesticideFragment();
        equipmentsFragment = new EquipmentsFragment();

        initializeFragment();

        Navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.pager);

                switch (item.getItemId()) {

                    case R.id.ferti:

                        replaceFragment(fertilizersFragment, currentFragment);
                        return true;

                    case R.id.pesti:

                        replaceFragment(pesticideFragment, currentFragment);
                        return true;

                    case R.id.equi:

                        replaceFragment(equipmentsFragment, currentFragment);
                        return true;

                    default:
                        return false;


                }

            }


        });
    }



    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.pager, fertilizersFragment);
        fragmentTransaction.add(R.id.pager, pesticideFragment);
        fragmentTransaction.add(R.id.pager, equipmentsFragment);

        fragmentTransaction.hide(equipmentsFragment);
        fragmentTransaction.hide(pesticideFragment);

        fragmentTransaction.commit();

    }
    private void replaceFragment(Fragment fragment, Fragment currentFragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == fertilizersFragment){

            fragmentTransaction.hide(pesticideFragment);
            fragmentTransaction.hide(equipmentsFragment);

        }

        if(fragment == pesticideFragment){

            fragmentTransaction.hide(fertilizersFragment);
            fragmentTransaction.hide(equipmentsFragment);

        }

        if(fragment == equipmentsFragment){

            fragmentTransaction.hide(fertilizersFragment);
            fragmentTransaction.hide(pesticideFragment);

        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;

    }

    */

