package com.test.sherlock.objects;

public class Book {

    private String title, text;
    private int  type, chapter;
//    private int page;

    public void setTitle(String title) {
        this.title = title;
    }

//    public void setPage(int page) {
//        this.page = page;
//    }

    public void setType(int type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

//    public int getPage() {
//        return page;
//    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getChapter() {
        return chapter;
    }
}
