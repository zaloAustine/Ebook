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

import com.google.android.material.chip.Chip;
import com.zalocoders.ebook.Adapter.CategoryAdapter;
import com.zalocoders.ebook.Adapter.CategoryAdapter2;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.ViewModels.Category.CategoriesViewModel;
import com.zalocoders.ebook.models.Category;

public class CategoryFragment extends Fragment {

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

        recycler2 = v.findViewById(R.id.recycler2);
        mLayoutManager1 = new LinearLayoutManager(mContext);
        recycler2.setLayoutManager(mLayoutManager1);
        recycler2.setNestedScrollingEnabled(false);
        recycler2.setHasFixedSize(true);



        fetchCategories();
        return v;
    }


    private void fetchCategories(){

        CategoryAdapter2 categoryAdapter = new CategoryAdapter2(mContext);


        CategoriesViewModel categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesViewModel.categoryPagedList.observe((LifecycleOwner) mContext, new Observer<PagedList<Category>>() {
            @Override
            public void onChanged(PagedList<Category> categories) {
                categoryAdapter.submitList(categories);
                recycler2.setAdapter(categoryAdapter);

            }
        });

    }
}
