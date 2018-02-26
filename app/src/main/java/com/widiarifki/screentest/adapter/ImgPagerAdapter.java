package com.widiarifki.screentest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by widiarifki on 26/02/2018.
 */

public class ImgPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ImgPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override

    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override

    public int getCount() {
        return this.fragments.size();
    }

}
