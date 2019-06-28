package com.example.sha.agro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by sha on 22-02-2019.
 */

public class AgriNewsTAA extends FragmentPagerAdapter
{

    public AgriNewsTAA(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {


        switch (i)
        {
            case 0:
                DailyNewsFragment dailyNewsFragment = new DailyNewsFragment();
                return  dailyNewsFragment;

            case 1:
                MonthlyUpdatesFragment monthlyUpdatesFragment = new MonthlyUpdatesFragment();
                return monthlyUpdatesFragment;

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

                return "Daily News";

            case 1:

                return "Monthly Updates";



            default:

                return null;
        }
    }
}
