package com.example.entrega_1_dev_mob;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numtab;
    public PagerAdapter(FragmentManager fm,int numtabs){
        super(fm);
        this.numtab = numtabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new page1();
            case 1:
                return new page2();
            case 2:
                return new page3();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numtab;
    }
}
