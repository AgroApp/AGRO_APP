package com.example.sha.agro;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Created by sha on 19-02-2019.
 */

public class TabAccessorAdapter extends FragmentPagerAdapter implements Serializable {


    private Bundle My_Lang;

    public TabAccessorAdapter(FragmentManager fm,Bundle My_Lang) {
        super(fm);
        this.My_Lang = My_Lang;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PesticideFragment pesticideFragment = new PesticideFragment();
                pesticideFragment.setArguments(My_Lang);
                return pesticideFragment;

            case 1:
                FertilizersFragment fertilizersFragment = new FertilizersFragment();
                fertilizersFragment.setArguments(My_Lang);
                return fertilizersFragment;

            case 2:
                EquipmentsFragment equipmentsFragment = new EquipmentsFragment();
                equipmentsFragment.setArguments(My_Lang);
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
