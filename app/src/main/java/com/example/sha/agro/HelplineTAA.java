package com.example.sha.agro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by sha on 09-03-2019.
 */

public class HelplineTAA extends TabAccessorAdapter {

    public HelplineTAA(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {


        switch (i)
        {
            case 0:
                HelplineFragment helplineFragment = new HelplineFragment();
                return  helplineFragment;



            default:

                return null;



        }




    }

    @Override
    public int getCount()
    {
        return 1;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {

        switch (position)
        {
            case 0:

                return "Helpline";





            default:

                return null;
        }
    }
}