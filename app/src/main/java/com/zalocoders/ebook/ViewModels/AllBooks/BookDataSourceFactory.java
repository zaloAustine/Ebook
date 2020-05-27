package com.zalocoders.ebook.ViewModels.AllBooks;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.zalocoders.ebook.models.Book;

public class BookDataSourceFactory extends BookDataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Book>>  itemLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public androidx.paging.DataSource create() {
        BookDataSource dataSource = new BookDataSource();
        itemLiveDataSource.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Book>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
