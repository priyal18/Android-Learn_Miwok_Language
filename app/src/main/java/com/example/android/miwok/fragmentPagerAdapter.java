package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 */
class fragmentPagerAdapter extends FragmentPagerAdapter {

    public fragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumberFragment();
        } else if (position == 1){
            return new colorsFragment();
        } else if (position == 2){
            return new familyFragment();
        } else {
            return new phrasesfragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Learn Numbers";
        }
        else if(position==1){
            return "Learn Colors";
        }
        else if(position==2){
            return "Family Fragment";
        }
        else{
            return "Phrases Fragment";
        }
    }
}
