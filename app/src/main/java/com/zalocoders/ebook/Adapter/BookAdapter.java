package com.zalocoders.ebook.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.databinding.BookItemBinding;
import com.zalocoders.ebook.models.Book;

import java.util.List;

public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

   private List<Book> mBookList;
    public boolean on_attach = true;
    private  Context mContext;

    public BookAdapter(List<Book> bookList, Context context) {
       this.mBookList = bookList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Book book = mBookList.get(position);
    holder.name.setText(book.getName());
    holder.author.setText(book.getAuthor());
    holder.percentage.setText(book.getPercentage());
    holder.mRatingBar.setRating(book.getRating());
    holder.mProgressBar.setProgress(book.getProgress());
    holder.category.setText(book.getCategory());
    holder.mSmartImageViewLayout.putImages(book.getImage());

        setAnimation(holder.itemView,position);
    }



    @Override
    public int getItemCount() {
        return mBookList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        SmartImageViewLayout mSmartImageViewLayout;
        TextView name,author,percentage;
        ProgressBar mProgressBar;
        RatingBar mRatingBar;
        Chip category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSmartImageViewLayout = itemView.findViewById(R.id.images);
            name = itemView.findViewById(R.id.name);
            author = itemView.findViewById(R.id.author);
            percentage = itemView.findViewById(R.id.percent);
            mProgressBar = itemView.findViewById(R.id.progress);
            mRatingBar = itemView.findViewById(R.id.rate);
            category = itemView.findViewById(R.id.category);

        }
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
