package com.zalocoders.ebook.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zalocoders.ebook.Views.fragments.AudioBookFragment;
import com.zalocoders.ebook.Views.fragments.CategoryFragment;
import com.zalocoders.ebook.Views.fragments.ChaptersFragment;
import com.zalocoders.ebook.Views.fragments.HomeFragment;
import com.zalocoders.ebook.Views.fragments.MyBookFragment;
import com.zalocoders.ebook.Views.fragments.SavedBooksFragment;


public class HomePageAdapter extends FragmentStatePagerAdapter {




    public HomePageAdapter(FragmentManager fm) {
        super(fm);

    }



    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new AudioBookFragment();
            case 2:
                return new CategoryFragment();
            case 3:
                return new MyBookFragment();
            case 4:
                return new ChaptersFragment();
            case 5:
                return new SavedBooksFragment();
            default:
                return null;


        }


    }

    @Override
    public int getCount() {
        return 6;
    }
}
