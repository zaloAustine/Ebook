package com.zalocoders.ebook.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.Views.Activities.ActionActivity;
import com.zalocoders.ebook.models.Book;
import com.zalocoders.ebook.models.Category;

import java.util.List;
import java.util.Objects;

import static maes.tech.intentanim.CustomIntent.customType;

public class CategoryAdapter extends PagedListAdapter<Category,CategoryAdapter.MyViewHolder> {

    public boolean on_attach = true;
    private  Context mContext;

    public CategoryAdapter(Context context) {
        super(DIFF_CALLBack);
        this.mContext = context;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.category_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    Category category = getItem(position);

    if(category!=null){

        holder.name.setText(category.getName());

        try {
           // holder.mSmartImageViewLayout.putImages(category.getImageUrl());

        }catch (NullPointerException e){

        }

    }

        setAnimation(holder.itemView,position);


    holder.materialCardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            Intent i  = new Intent(mContext, ActionActivity.class);
            i.putExtra("id",category.getCategoryId());
            i.putExtra("name",category.getName());
            mContext.startActivity(i);
            customType(mContext,"bottom-to-up");


        }
    });


    }





    public static class MyViewHolder extends RecyclerView.ViewHolder{
        SmartImageViewLayout mSmartImageViewLayout;
        TextView name,author;
        CardView materialCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSmartImageViewLayout = itemView.findViewById(R.id.images);
            name = itemView.findViewById(R.id.name);
            author =  itemView.findViewById(R.id.author);
            materialCardView = itemView.findViewById(R.id.materialCardView);

        }
    }


    private static DiffUtil.ItemCallback<Category> DIFF_CALLBack = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
          return   oldItem.getCategoryId().equals(newItem.getCategoryId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };


    public void setAnimation(View itemview, int i) {

        if (!on_attach) {
            i = -1;
        }


        boolean isNotFirst = i == -1;
        i++;

        itemview.setAlpha(0.f);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemview, "alpha", 0.f, 0.5f, 1.0f);
        animator.setStartDelay(isNotFirst ? 500 / 2 : (i * 500 / 3));
        animator.setDuration(500);
        set.play(animator);
        animator.start();

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);

    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
