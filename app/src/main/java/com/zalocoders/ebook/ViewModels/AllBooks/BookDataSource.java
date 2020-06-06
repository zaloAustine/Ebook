package com.zalocoders.ebook.ViewModels.AllBooks;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zalocoders.ebook.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDataSource extends PageKeyedDataSource<Integer, Book> {
    public static final int PAGE_SIZE = 50;
    public static final int FIRST_PAGE = 1;
    public static final String SITE_NAME = "Ebook";

    List<Book> mBookList;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Book> callback) {

      mBookList = new ArrayList<>();

load();


        callback.onResult(mBookList,null,FIRST_PAGE+1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {

        mBookList = new ArrayList<>();

     load();
        callback.onResult(mBookList,params.key);

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {


        load();

        callback.onResult(mBookList,params.key);



    }

    public void load(){

        Book book = new Book();
        book.setAuthor("By Kobe Bryant, Phil Jackson");
        book.setBookId("44");
        book.setImage("https://www.placeinprint.com/sites/default/files/products/list/EastLondonOpinionated_front2.png");
        book.setName("The Mamba Mentality");
        book.setPercentage("50");
        book.setProgress(50);
        book.setBookMarked("yes");
        book.setCategory("Education");
        book.setRating(2);


        Book book1 = new Book();
        book1.setAuthor("Steve Jobs");
        book1.setBookId("44");
        book1.setImage("https://piccadillybooks.com/wp-content/uploads/2015/10/Make-Money-Reading-Books-Front-Cover-600x600.png");
        book1.setPercentage("50");
        book1.setProgress(50);
        book1.setBookMarked("no");
        book1.setName("The messy Middle");
        book1.setCategory("Education");
        book1.setRating(2);


        Book book2 = new Book();
        book2.setAuthor("Zalo Austine");
        book2.setBookId("44");
        book2.setImage("https://static.wixstatic.com/media/5933ad_4749b11fb41e4cc3a19c25e245c884d7~mv2.png/v1/fill/w_296,h_388,al_c,q_85,usm_0.66_1.00_0.01/book-cover-front.webp");
        book2.setName("The messy Middle");
        book2.setPercentage("50");
        book2.setBookMarked("yes");
        book2.setProgress(50);
        book2.setCategory("Education");
        book2.setRating(2);



        mBookList.add(book);
        mBookList.add(book1);
        mBookList.add(book2);


    }
}
