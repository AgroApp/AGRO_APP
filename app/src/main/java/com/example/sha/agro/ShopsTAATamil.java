package com.example.sha.agro;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class ShopsTAATamil extends FragmentPagerAdapter {



    public ShopsTAATamil(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {




        switch (i) {
            case 0:
                PesticideFragment pesticideFragment = new PesticideFragment();

                return pesticideFragment;

            case 1:
                FertilizerTamilFragment fertilizersFragment = new FertilizerTamilFragment();

                return fertilizersFragment;

            case 2:
                EquipmentsFragment equipmentsFragment = new EquipmentsFragment();
                return equipmentsFragment;

            default:

                return null;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        switch (position) {
            case 0:
                // String peststring =  Resources.getSystem().getString(R.string.pesticide);
                return   "pesticide";


            case 1:
                // String ferstring =  Resources.getSystem().getString(R.string.fertilizer);
                return   "fertilizer";

            case 2:
                //String seestring =  Resources.getSystem().getString(R.string.seedshop);
                return   "Seed shop";


            default:

                return null;
        }
    }

}
