package com.zalocoders.ebook.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;
import com.zalocoders.ebook.Adapter.HomePageAdapter;
import com.zalocoders.ebook.R;


public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    TabLayout tabLayout;
    MaterialToolbar materialToolbar;
    HomePageAdapter pagerAdapter;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        materialToolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(materialToolbar);




        Navigations();

    }




    public void Navigations(){

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout);


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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.library:
                Intent i = new Intent(this, LibraryActivity.class);
                startActivity(i);
                break;
            case R.id.account:
                Intent i2 = new Intent(this, AccountActivity.class);
                startActivity(i2);
                break;
        }

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

       MenuItem item = menu.findItem(R.id.home);

        inflater.inflate(R.menu.menu_home, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
