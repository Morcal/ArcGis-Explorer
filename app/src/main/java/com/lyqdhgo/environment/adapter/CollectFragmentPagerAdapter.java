package com.lyqdhgo.environment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lyqdhgo.environment.ui.collect.child.EditDataFragment;
import com.lyqdhgo.environment.ui.collect.child.PhotoFragment;

/**
 * Created by QiDeHong on 2017/4/19.
 */

public class CollectFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTab = new String[]{"Collect", "Photo"};

    public CollectFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return EditDataFragment.newInstance("");
        } else {
            return PhotoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
