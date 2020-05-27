package com.zalocoders.ebook.models;

public class Chapter {

    String bookName,image,fileUlr,chapterId,description,chapter;

    public String getBookName() {
        return bookName;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFileUlr() {
        return fileUlr;
    }

    public void setFileUlr(String fileUlr) {
        this.fileUlr = fileUlr;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
