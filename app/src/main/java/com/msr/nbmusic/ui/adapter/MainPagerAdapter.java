package com.msr.nbmusic.ui.adapter;

import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.msr.nbmusic.R;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {

    public static final int VIEW_SIZE = 4;
    private Class[] fragments;

    public MainPagerAdapter(FragmentManager fm, Class[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) fragments[position].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return VIEW_SIZE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "first";
            case 1:
                return "second";
            case 2:
                return "third";
            case 3:
                return "fourth";
            default:
                break;
        }
        return "";
    }

    @Override
    public Integer getPageIcon(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_main_n;
            case 1:
                return R.drawable.ic_categry_n;
            case 2:
                return R.drawable.ic_live_n;
            case 3:
                return R.drawable.ic_mine_n;
            default:
                break;
        }
        return 0;
    }

    @Override
    public Integer getPageSelectIcon(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_main_f_n;
            case 1:
                return R.drawable.ic_categry_f_n;
            case 2:
                return R.drawable.ic_live_f_n;
            case 3:
                return R.drawable.ic_mine_f_n;
            default:
                break;
        }
        return 0;
    }

    @Override
    public Rect getPageIconBounds(int position) {
        return null;
    }
}
