package com.zalocoders.ebook.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.zalocoders.ebook.Views.fragments.MyBookFragment;
import com.zalocoders.ebook.Views.fragments.ChaptersFragment;


public class PageAdapter extends FragmentStatePagerAdapter {




    public PageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            return new MyBookFragment();
        }
        return new ChaptersFragment();


    }

    @Override
    public int getCount() {
        return 2;
    }
}
