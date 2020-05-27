package com.zalocoders.ebook.Views.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.teleclinic.bulent.smartimageview.SmartImageView;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.Adapter.AllBookAdapter;
import com.zalocoders.ebook.Adapter.CategoryAdapter;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.Utils.BookItemClick;
import com.zalocoders.ebook.ViewModels.AllBooks.BookViewModel;
import com.zalocoders.ebook.ViewModels.Category.CategoriesViewModel;
import com.zalocoders.ebook.Views.Activities.MainActivity;
import com.zalocoders.ebook.Views.Activities.ViewBookActivity;
import com.zalocoders.ebook.models.Book;
import com.zalocoders.ebook.models.Category;

public class HomeFragment extends Fragment implements BookItemClick {
   private Context mContext;
    private LinearLayoutManager mLayoutManager1,mLinearLayoutManager,mLinearLayoutManager3,mLinearLayoutManager4;
   private RecyclerView recycler,recycler2,recycler3,recycler4;
    private View v;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);


        recycler2 = v.findViewById(R.id.recycler2);
        mLayoutManager1 = new GridLayoutManager(mContext,2,GridLayoutManager.HORIZONTAL,false);
        recycler2.setLayoutManager(mLayoutManager1);
        recycler2.setNestedScrollingEnabled(false);
        recycler2.setHasFixedSize(true);

        recycler = v.findViewById(R.id.recycler);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(mLinearLayoutManager);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);


        recycler3 = v.findViewById(R.id.recycler3);
        mLinearLayoutManager3 = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        recycler3.setLayoutManager(mLinearLayoutManager3);
        recycler3.setHasFixedSize(true);
        recycler3.setNestedScrollingEnabled(false);



        recycler4 = v.findViewById(R.id.recycler4);
        mLinearLayoutManager4 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
        recycler4.setLayoutManager(mLinearLayoutManager4);
        recycler4.setHasFixedSize(true);
        recycler4.setNestedScrollingEnabled(false);


        FetchAllBooks();
        fetchCategories();


        initViews();





        return v;
    }


    private void initViews() {



    }

    public void FetchAllBooks(){

        BookViewModel bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        AllBookAdapter mBookAdapter = new AllBookAdapter(mContext,this);

        bookViewModel.bookPagedList.observe((LifecycleOwner) mContext, new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                mBookAdapter.submitList(books);
                recycler.setAdapter(mBookAdapter);
                recycler3.setAdapter(mBookAdapter);
                recycler4.setAdapter(mBookAdapter);

            }
        });



    }


    private void fetchCategories(){

        CategoryAdapter categoryAdapter = new CategoryAdapter(mContext);


        CategoriesViewModel categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.categoryPagedList.observe((LifecycleOwner) mContext, new Observer<PagedList<Category>>() {
            @Override
            public void onChanged(PagedList<Category> categories) {
                categoryAdapter.submitList(categories);
                recycler2.setAdapter(categoryAdapter);

            }
        });

    }

    @Override
    public void onBookCliked(Book book, SmartImageViewLayout imageView) {


        //  image,name,author,rating
        Intent i = new Intent(mContext, ViewBookActivity.class);
        i.putExtra("name",book.getName());
        i.putExtra("author",book.getAuthor());
        i.putExtra("id",book.getBookId());
        i.putExtra("image",book.getImage());
        i.putExtra("rating",String.valueOf(book.getRating()));
        i.putExtra("category",book.getCategory());


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"image");
        startActivity(i,options.toBundle());

    }
}
