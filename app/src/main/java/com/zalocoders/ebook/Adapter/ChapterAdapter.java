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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.R;
import com.zalocoders.ebook.models.Book;
import com.zalocoders.ebook.models.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

   private List<Chapter> mBookList;
    public boolean on_attach = true;
    private  Context mContext;

    public ChapterAdapter(List<Chapter> bookList, Context context) {
       this.mBookList = bookList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.chapter_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Chapter book = mBookList.get(position);
    holder.name.setText(book.getBookName());
    holder.description.setText(book.getDescription());
    holder.mSmartImageViewLayout.putImages(book.getImage());
    holder.chapter.setText(book.getChapter());



        setAnimation(holder.itemView,position);
    }



    @Override
    public int getItemCount() {
        return mBookList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        SmartImageViewLayout mSmartImageViewLayout;
        TextView name,description;
        MaterialButton chapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSmartImageViewLayout = itemView.findViewById(R.id.images);
            name = itemView.findViewById(R.id.name);
            chapter = itemView.findViewById(R.id.chapter);
            description = itemView.findViewById(R.id.description);


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
