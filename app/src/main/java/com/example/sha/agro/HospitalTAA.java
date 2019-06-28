package com.example.sha.agro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by sha on 09-03-2019.
 */

public class HospitalTAA extends TabAccessorAdapter
{

    public HospitalTAA(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int i)
        {


            switch (i)
            {
                case 0:
                    HospitalFragment hospitalFragment = new HospitalFragment();
                    return  hospitalFragment;

                case 1:
                    VeterinaryHospitalFragment veterinaryHospitalFragment = new VeterinaryHospitalFragment();
                    return veterinaryHospitalFragment;



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

                    return "Hospitals";

                case 1:

                    return "Veterinary Hospitals";


                default:

                    return null;
            }
        }


}
