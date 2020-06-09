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
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnLongPressListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
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
import com.zalocoders.ebook.models.DefaultLinkHandler2;

import java.util.ArrayList;
import java.util.List;

public class ViewBookActivity extends AppCompatActivity   implements BookItemClick{


    SmartImageViewLayout images;
    TextView author;
    RatingBar rate;
    View bottomsheetView;
    Chip category;
    PDFView pdfView;

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

        pdfView = bottomsheetView.findViewById(R.id.pdfView);


        bottomSheetDialog.setContentView(bottomsheetView);
        bottomSheetDialog.show();

        loadPdf();

    }


    public void loadPdf(){
        pdfView.fromUri(Uri.parse("http://maven.apache.org/maven-1.x/maven.pdf"))
                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                // allows to draw something on all pages, separately for every page. Called only for visible pages
                .onDrawAll(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                    }
                }) // called after document is loaded and starts to be rendered
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {

                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(ViewBookActivity.this,t.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Toast.makeText(ViewBookActivity.this,t.getMessage().toString(),Toast.LENGTH_SHORT).show();

                    }
                })
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages) {

                    }
                }) // called after document is rendered for the first time
                // called on single tap, return true if handled, false to toggle scroll handle visibility
                .onTap(new OnTapListener() {
                    @Override
                    public boolean onTap(MotionEvent e) {
                        return false;
                    }
                })
                .onLongPress(new OnLongPressListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {

                    }
                })
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                .linkHandler(new DefaultLinkHandler(pdfView))
                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
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
                super.onBackPressed();
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
