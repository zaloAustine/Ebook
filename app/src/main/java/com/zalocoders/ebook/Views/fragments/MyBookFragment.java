package com.zalocoders.ebook.Views.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zalocoders.ebook.Adapter.BookAdapter;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.ViewModels.Category.CategoriesViewModel;
import com.zalocoders.ebook.models.Book;

import java.util.ArrayList;
import java.util.List;

public class MyBookFragment extends Fragment {

    private CategoriesViewModel mViewModel;
    Context context;
    View v;
    List<Book> mBookList;
    LinearLayoutManager mLayoutManager;
    RecyclerView recycler;

    public static MyBookFragment newInstance() {
        return new MyBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.chapters_fragment, container, false);

        recycler = v.findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(context);

        recycler.setLayoutManager(mLayoutManager);
        Fetch();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onAttach(@NonNull Context contextp) {
        super.onAttach(context);
        context = contextp;

    }


    public void Fetch(){
        mBookList = new ArrayList<>();

        Book book = new Book();
        book.setAuthor("Zalo Austine");
        book.setBookId("44");
        book.setImage("https://www.creativindie.com/wp-content/uploads/2012/07/royalty-free.jpg");
        book.setName("The messy Middle");
        book.setPercentage("50");
        book.setProgress(50);
        book.setCategory("Education");
        book.setRating(2);

        for(int i=0;i<10;i++){

            mBookList.add(book);
        }

        BookAdapter mBookAdapter = new BookAdapter(mBookList, context);
        recycler.setNestedScrollingEnabled(false);
        recycler.setAdapter(mBookAdapter);
        mBookAdapter.notifyDataSetChanged();
    }
}
