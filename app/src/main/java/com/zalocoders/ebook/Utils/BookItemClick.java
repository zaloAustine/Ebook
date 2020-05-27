package com.zalocoders.ebook.Utils;

import com.teleclinic.bulent.smartimageview.SmartImageView;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.ebook.models.Book;

public interface BookItemClick {

    void onBookCliked(Book book, SmartImageViewLayout imageView);
}
