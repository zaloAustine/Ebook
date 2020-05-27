package com.zalocoders.ebook.Views.fragments;

import android.content.Context;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zalocoders.ebook.Adapter.AllBookAdapter;
import com.zalocoders.ebook.Adapter.AllBookAdapter2;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.ViewModels.AllBooks.BookViewModel;
import com.zalocoders.ebook.models.Book;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioBookFragment extends Fragment {
    private Context mContext;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recycler;
    private View v;

    public AudioBookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_audio_book, container, false);



        recycler = v.findViewById(R.id.recycler);
        mLinearLayoutManager = new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(mLinearLayoutManager);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);


        FetchAllBooks();

        return v;
    }

    public void FetchAllBooks(){

        BookViewModel bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        AllBookAdapter2 mBookAdapter = new AllBookAdapter2(mContext);

        bookViewModel.bookPagedList.observe((LifecycleOwner) mContext, new Observer<PagedList<Book>>() {
            @Override
            public void onChanged(PagedList<Book> books) {
                mBookAdapter.submitList(books);
                recycler.setAdapter(mBookAdapter);

            }
        });





    }

}
