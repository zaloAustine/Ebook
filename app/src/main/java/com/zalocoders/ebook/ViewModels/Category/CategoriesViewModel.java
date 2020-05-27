package com.zalocoders.ebook.ViewModels.Category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.zalocoders.ebook.models.Category;

public class CategoriesViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public LiveData<PagedList<Category>> categoryPagedList;
    LiveData<PageKeyedDataSource<Integer,Category>> liveDataSource;

    public CategoriesViewModel() {

        CategoryDataSourceFactory DataSourceFactory = new CategoryDataSourceFactory();
        liveDataSource = DataSourceFactory.getCategoryLiveDataSource();
        PagedList.Config  config= new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setPageSize(CategoryDataSource.PAGE_SIZE)
                .build();

        categoryPagedList = (new LivePagedListBuilder(DataSourceFactory,config).build());
    }
}
