package com.zalocoders.ebook.ViewModels.Category;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zalocoders.ebook.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDataSource extends PageKeyedDataSource<Integer, Category> {

    public static final int PAGE_SIZE = 50;
    public static final int FIRST_PAGE = 1;
    public static final String SITE_NAME = "Ebook";
    List<Category> mCategoryList;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Category> callback) {

        mCategoryList = new ArrayList<>();


        load();

        callback.onResult(mCategoryList,null,FIRST_PAGE+1);

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Category> callback) {
        mCategoryList = new ArrayList<>();



       // load();



        callback.onResult(mCategoryList,params.key);

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Category> callback) {


        mCategoryList = new ArrayList<>();


       // load();


        callback.onResult(mCategoryList,params.key);
    }

    public void load(){

        Category category = new Category();
        category.setName("Comic Book or Graphic Novel");
        mCategoryList.add(category);

        Category category1 = new Category();
        category1.setName("Action and Adventure.");
        mCategoryList.add(category1);

        Category category2 = new Category();
        category2.setName("Classics");
        mCategoryList.add(category2);

        Category category3 = new Category();
        category3.setName("Historical Fiction");
        mCategoryList.add(category3);


        Category category4 = new Category();
        category4.setName("Horror");
        mCategoryList.add(category4);



    }
}
