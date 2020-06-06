package com.zalocoders.ebook.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.zalocoders.ebook.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class ActionActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewController();


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:

                startActivity(new Intent(this,MainActivity.class));
                customType(ActionActivity.this,"up-to-bottom");

        }
        return true;
    }


    public void viewController(){

        Intent i = getIntent();

        if(i.hasExtra("name")){

            try {

                String name = i.getStringExtra("name");
                getSupportActionBar().setTitle(name);

            }catch (NullPointerException e){

            }

        }

    }
}
