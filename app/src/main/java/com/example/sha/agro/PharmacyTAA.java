package com.example.sha.agro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by sha on 09-03-2019.
 */

public class PharmacyTAA extends FragmentPagerAdapter {

    public PharmacyTAA(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {


        switch (i)
        {
            case 0:
                PharmacyFragment pharmacyFragment = new PharmacyFragment();
                return  pharmacyFragment;

            case 1:
                VeterinaryPharmacyFragment veterinaryPharmacyFragment = new VeterinaryPharmacyFragment();
                return veterinaryPharmacyFragment;

            default:

                return null;



        }




    }

    @Override
    public int getCount()
    {
        return 2;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {

        switch (position)
        {
            case 0:

                return "Pharmacy";

            case 1:

                return "Veterinary Pharmacy";



            default:

                return null;
        }
    }

}
