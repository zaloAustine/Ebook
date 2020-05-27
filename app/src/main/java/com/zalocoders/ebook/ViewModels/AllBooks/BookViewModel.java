package com.zalocoders.ebook.ViewModels.AllBooks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.zalocoders.ebook.models.Book;

public class BookViewModel extends ViewModel {

    public LiveData<PagedList<Book>> bookPagedList;
     LiveData<PageKeyedDataSource<Integer,Book>> liveDataSource;

    public BookViewModel() {

        BookDataSourceFactory itemDataSourceFactory = new BookDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        PagedList.Config  config= new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setPageSize(BookDataSource.PAGE_SIZE)
                .build();

        bookPagedList = (new LivePagedListBuilder(itemDataSourceFactory,config).build());
    }
}
