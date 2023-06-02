package com.realappraiser.gharvalue.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.realappraiser.gharvalue.fragments.OtherDetailsFragment;
import com.realappraiser.gharvalue.fragments.OtherDetailsFragment_ka;
import com.realappraiser.gharvalue.fragments.PhotoLatLong;
import com.realappraiser.gharvalue.fragments.PhotoLatLong_ka;

/**
 * Created by kaptas on 19/12/17.
 */

public class PhotoOthersTabAdapter extends FragmentStatePagerAdapter {
    @SuppressWarnings("CanBeFinal")
    private String[] tabTitles = new String[]{"Photo/LatLng Details", "Other Details"};
    boolean is_real_appraiser_jaipur = false;

    public PhotoOthersTabAdapter(FragmentManager supportFragmentManager,boolean is_real_appraiser_jaipur_) {
        super(supportFragmentManager);
        this.is_real_appraiser_jaipur = is_real_appraiser_jaipur_;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
         if(is_real_appraiser_jaipur) {
            // Jaipur Tab
            switch (position) {
                case 0:
                    @SuppressWarnings("UnnecessaryLocalVariable") PhotoLatLong tab1 = new PhotoLatLong();
                    return tab1;
                case 1:
                    @SuppressWarnings("UnnecessaryLocalVariable") OtherDetailsFragment tab2 = new OtherDetailsFragment();
                    return tab2;
                default:
                    return null;
            }
        }else {
            // Kakode Tab
            switch (position) {
                case 0:
                    @SuppressWarnings("UnnecessaryLocalVariable") PhotoLatLong_ka tab1 = new PhotoLatLong_ka();
                    return tab1;
                case 1:
                    @SuppressWarnings("UnnecessaryLocalVariable") OtherDetailsFragment_ka tab2 = new OtherDetailsFragment_ka();
                    /*@SuppressWarnings("UnnecessaryLocalVariable")
                    OtherDetails_ka tab2 = new OtherDetails_ka();*/
                    return tab2;
                default:
                    return null;
            }
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }


}

