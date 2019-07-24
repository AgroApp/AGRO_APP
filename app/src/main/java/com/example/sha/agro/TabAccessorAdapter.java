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


    private Bundle lang;

    public TabAccessorAdapter(FragmentManager fm,Bundle lang) {
        super(fm);
        this.lang = lang;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PesticideFragment pesticideFragment = new PesticideFragment();
                pesticideFragment.setArguments(lang);
                return pesticideFragment;

            case 1:
                FertilizersFragment fertilizersFragment = new FertilizersFragment();
                fertilizersFragment.setArguments(lang);
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

                    return  "pesticide";


                case 1:
                    return "fertilizer";
                    //return Resources.getSystem().getString(R.string.fertilizer);
                case 2:
                    return "seed shop";
                    //return Resources.getSystem().getString(R.string.equipments);

                default:

                    return null;
            }
        }


}
