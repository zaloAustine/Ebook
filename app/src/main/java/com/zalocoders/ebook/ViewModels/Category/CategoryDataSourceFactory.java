package com.zalocoders.ebook.ViewModels.Category;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.zalocoders.ebook.models.Category;

public class CategoryDataSourceFactory extends CategoryDataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Category>>  categoryLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        CategoryDataSource dataSource = new CategoryDataSource();
        categoryLiveDataSource.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Category>> getCategoryLiveDataSource() {
        return categoryLiveDataSource;
    }
}
