package com.example.sha.agro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by sha on 08-03-2019.
 */

public class BankloanTAA extends FragmentPagerAdapter {

    public BankloanTAA(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {


        switch (i)
        {
            case 0:
                IndianBankFragment indianBankFragment = new IndianBankFragment();
                return  indianBankFragment;

            case 1:
                CanaraBanFragment canaraBanFragment = new CanaraBanFragment();
                return canaraBanFragment;

            case 2:
                AxisBankFragment axisBankFragment = new AxisBankFragment();
                return axisBankFragment;

            case 3:
                CorporationBankFragment corporationBankFragment = new CorporationBankFragment();
                return corporationBankFragment;

            default:

                return null;



        }




    }

    @Override
    public int getCount()
    {
        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {

        switch (position)
        {
            case 0:

                return "Indian Bank";

            case 1:

                return "Canara Bank";

            case 2:

                return "Axis Bank";


            case 3:

                return "Corporation Bank";




            default:

                return null;
        }
    }
}
