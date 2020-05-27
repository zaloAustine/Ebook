package com.zalocoders.ebook.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;
import com.zalocoders.ebook.Adapter.HomePageAdapter;
import com.zalocoders.ebook.Adapter.PageAdapter;
import com.zalocoders.ebook.R;


public class MainActivity extends AppCompatActivity {


    Chip library;
    ViewPager viewPager;
    TabLayout tabLayout;
    MaterialToolbar materialToolbar;
    HomePageAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        Navigations();

    }

    private void initViews() {





    }





    public void Navigations(){

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout);

        materialToolbar = findViewById(R.id.materialToolbar);

        tabLayout.canScrollHorizontally(View.SCROLL_AXIS_HORIZONTAL);


        pagerAdapter = new HomePageAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));






    }




}
