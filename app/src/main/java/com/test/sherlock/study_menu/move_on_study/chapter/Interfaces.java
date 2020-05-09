package com.test.sherlock.study_menu.move_on_study.chapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.sherlock.objects.Book;

import java.util.List;

public interface Interfaces {

    interface View{
        void setMainTitle(String main_title);
        void addViewInContainer(android.view.View v);
    }

    interface Presenter{
        void getViews(LayoutInflater inflater,int type, int chapter, ViewGroup parent);
    }

    interface Model{
        List<Book> getChapterTextByTypeAndChapter(int type, int chapter);
    }

}
