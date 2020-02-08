package com.example.snapfood;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int size =3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                Log.d("bugg","position: "+position);
                return PastRecipeFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                Log.d("bugg","position: "+position);
                return CameraFragment.newInstance(1, "Page # 2");

            case 2: // Fragment # 1 - This will show SecondFragment
                Log.d("bugg","position: "+position);
                return SearchRecipeFragment.newInstance(2, "Page # 3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return size;
    }
}
