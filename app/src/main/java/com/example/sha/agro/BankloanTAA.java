package com.example.sha.agro;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.io.Serializable;

/**
 * Created by sha on 08-03-2019.
 */

public class BankloanTAA extends FragmentPagerAdapter implements Serializable {
    private Bundle My_Lang;
    public BankloanTAA(FragmentManager fm,Bundle My_Lang)
    {
        super(fm);
        this.My_Lang = My_Lang;
    }

    @Override
    public Fragment getItem(int i)
    {


        switch (i)
        {
            case 0:
                IndianBankFragment indianBankFragment = new IndianBankFragment();
                indianBankFragment.setArguments(My_Lang);
                return  indianBankFragment;

            case 1:
                CanaraBanFragment canaraBanFragment = new CanaraBanFragment();
                canaraBanFragment.setArguments(My_Lang);
                return canaraBanFragment;



            case 2:
                CorporationBankFragment corporationBankFragment = new CorporationBankFragment();
                corporationBankFragment.setArguments(My_Lang);
                return corporationBankFragment;

            default:

                return null;



        }




    }

    @Override
    public int getCount()
    {
        return 3;
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

                return "Corporation Bank";




            default:

                return null;
        }
    }
}
