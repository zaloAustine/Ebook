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
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.Views.Activities.ViewBookActivity;
import com.zalocoders.ebook.models.Book;

import java.util.Objects;

public class AllBookAdapter2 extends PagedListAdapter<Book, AllBookAdapter2.MyViewHolder> {

    private  Context mContext;
    public boolean on_attach = true;


    public AllBookAdapter2(Context context) {
        super(DIFF_CALLBack);
        this.mContext = context;
    }


private static DiffUtil.ItemCallback<Book> DIFF_CALLBack = new DiffUtil.ItemCallback<Book>() {
    @Override
    public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
        return oldItem.getBookId().equals(newItem.getBookId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
        return Objects.equals(oldItem, newItem);
    }
};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.allbook_item2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    Book book = getItem(position);

    if(book!=null){
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.mRatingBar.setRating(book.getRating());
        holder.mSmartImageViewLayout.putImages(book.getImage());

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, ViewBookActivity.class);
                i.putExtra("name",book.getName());
                i.putExtra("author",book.getAuthor());
                i.putExtra("id",book.getBookId());
                i.putExtra("image",book.getImage());
                i.putExtra("rating",String.valueOf(book.getRating()));
                i.putExtra("category",book.getCategory());

                mContext.startActivity(i);

            }
        });
    }



        setAnimation(holder.itemView,position);
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



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        SmartImageViewLayout mSmartImageViewLayout;
        TextView name,author;
        RatingBar mRatingBar;
        MaterialCardView book;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSmartImageViewLayout = itemView.findViewById(R.id.images);
            name = itemView.findViewById(R.id.name);
            author = itemView.findViewById(R.id.author);
            mRatingBar = itemView.findViewById(R.id.rate);
            book = itemView.findViewById(R.id.book);

        }
    }


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
}
