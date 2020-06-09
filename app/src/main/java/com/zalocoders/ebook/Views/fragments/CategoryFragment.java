package com.zalocoders.ebook.Views.fragments;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.Adapter.CategoryAdapter;
import com.zalocoders.ebook.Adapter.TopBookAdapter;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.Utils.BookItemClick;
import com.zalocoders.ebook.ViewModels.AllBooks.BookViewModel;
import com.zalocoders.ebook.ViewModels.Category.CategoriesViewModel;
import com.zalocoders.ebook.Views.Activities.ViewBookActivity;
import com.zalocoders.ebook.models.Book;
import com.zalocoders.ebook.models.Category;

public class CategoryFragment extends Fragment implements BookItemClick {

    Context mContext;
    private LinearLayoutManager mLayoutManager1;
    private RecyclerView recycler2;
    private View v;



    public CategoryFragment() {
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
        v = inflater.inflate(R.layout.fragment_category, container, false);




        fetchTopBooks();
        fetchCategories2();
        return v;
    }


    private void fetchTopBooks(){

        recycler2 = v.findViewById(R.id.recycler2);
        mLayoutManager1 = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        recycler2.setLayoutManager(mLayoutManager1);

        TopBookAdapter adapter = new TopBookAdapter(mContext,this);


        BookViewModel bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        bookViewModel.bookPagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                adapter.submitList(books);
                recycler2.setAdapter(adapter);
            }
        });


    }


    private void fetchCategories2(){

       RecyclerView recycler = v.findViewById(R.id.recycler);
      LinearLayoutManager  mLinearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(mLinearLayoutManager);


        CategoryAdapter categoryAdapter = new CategoryAdapter(mContext);


        CategoriesViewModel categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.categoryPagedList.observe((LifecycleOwner) mContext, new Observer<PagedList<Category>>() {
            @Override
            public void onChanged(PagedList<Category> categories) {
                categoryAdapter.submitList(categories);
                recycler.setAdapter(categoryAdapter);

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
