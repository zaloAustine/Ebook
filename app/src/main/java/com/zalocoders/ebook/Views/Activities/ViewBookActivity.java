package com.zalocoders.ebook.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.teleclinic.bulent.smartimageview.SmartImageView;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.Adapter.AllBookAdapter;
import com.zalocoders.ebook.Adapter.CategoryAdapter;
import com.zalocoders.ebook.Adapter.ChapterAdapter;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.Utils.BookItemClick;
import com.zalocoders.ebook.ViewModels.AllBooks.BookViewModel;
import com.zalocoders.ebook.models.Book;
import com.zalocoders.ebook.models.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ViewBookActivity extends AppCompatActivity   implements BookItemClick{


    SmartImageViewLayout images;
    TextView author;
    RatingBar rate;
    View bottomsheetView;
    Chip category;
    TextView header;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        images = findViewById(R.id.images);
        author = findViewById(R.id.author);
        rate = findViewById(R.id.rate);
        category = findViewById(R.id.category);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recieveIntent();

        MaterialButton preview = findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet();
            }
        });





        FetchAllBooks();
    }

    private void bottomSheet() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this,R.style.AppBottomSheetDialogTheme);
         bottomsheetView = this.getLayoutInflater().inflate(R.layout.layout_bottom_sheet,null);

         header = (TextView) bottomsheetView.findViewById(R.id.header);
        header.setText(getIntent().getExtras().getString("name"));


        bottomSheetDialog.setContentView(bottomsheetView);
        bottomSheetDialog.show();
    }






    public void Fetch(){

       RecyclerView recycler = findViewById(R.id.recycler);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(mLayoutManager);

        List<Chapter> mChapters = new ArrayList<>();


        for(int i=0;i<10;i++){

            Chapter chapter = new Chapter();
            chapter.setImage("https://www.creativindie.com/wp-content/uploads/2012/07/royalty-free.jpg");
            chapter.setBookName("The messy Middle");
            chapter.setDescription("The mind can be a fragile gift. Sometimes it fails us, and often when we need it most. Happily, mine appears to be working reliably now, and so I am perhaps as able as most to confront and cope with the madness of the moment. But during a lengthy period some fifty years ago, my mind unexpectedly broke down");

            chapter.setChapter(String.valueOf(i));
            mChapters.add(chapter);
        }

        ChapterAdapter chapterAdapter = new ChapterAdapter(mChapters, this);
        recycler.setAdapter(chapterAdapter);


        chapterAdapter.notifyDataSetChanged();
    }

    public void recieveIntent(){



            String nameT = getIntent().getExtras().getString("name");
            String authort = getIntent().getExtras().getString("author");
            String id = getIntent().getExtras().getString("id");
            String image = getIntent().getExtras().getString("image");
            String rating = getIntent().getExtras().getString("rating");
            String categoryT = getIntent().getExtras().getString("category");


            getSupportActionBar().setTitle(nameT);


            images.putImages(image);
            author.setText(authort);
            category.setText(categoryT);
            rate.setRating(Integer.valueOf(rating));


    }



    public void FetchAllBooks(){

       RecyclerView recycler = findViewById(R.id.recycler);
      LinearLayoutManager  mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(mLinearLayoutManager);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        BookViewModel bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        AllBookAdapter mBookAdapter = new AllBookAdapter(this,this);

        bookViewModel.bookPagedList.observe((LifecycleOwner) this, new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                mBookAdapter.submitList(books);
                recycler.setAdapter(mBookAdapter);


            }
        });



    }


    @Override
    public void onBookCliked(Book book, SmartImageViewLayout imageView) {

        //  image,name,author,rating
        Intent i = new Intent(ViewBookActivity.this, ViewBookActivity.class);
        i.putExtra("name",book.getName());
        i.putExtra("author",book.getAuthor());
        i.putExtra("id",book.getBookId());
        i.putExtra("image",book.getImage());
        i.putExtra("rating",String.valueOf(book.getRating()));
        i.putExtra("category",book.getCategory());


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ViewBookActivity.this,imageView,"image");
        startActivity(i,options.toBundle());


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(this,MainActivity.class));

        }
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }
}
