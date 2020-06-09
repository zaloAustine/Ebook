package com.zalocoders.ebook.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zalocoders.ebook.Views.fragments.AccountSettingsFragment;
import com.zalocoders.ebook.Views.fragments.CategoryFragment;
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
                return new CategoryFragment();
            case 2:
                return new SavedBooksFragment();
            case 3:
                return new MyBookFragment();
            case 4:
                return new AccountSettingsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
