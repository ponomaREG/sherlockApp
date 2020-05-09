package com.test.sherlock.study_menu.move_on_study;

import android.content.Context;

import com.test.sherlock.objects.Book;

import java.util.List;

public interface Interfaces {

    interface View{
        void startNextActivity(int type, int chapter);
        void setAdapter(RV_study adapter);
    }

    interface Presenter{
        void getAdapter(Context context, int type);
        void OnChapterClick(android.view.View v);

        interface connectionBetweenRVandView{
            void tellViewToStartNextActivity(int type, int chapter);
        }
    }

    interface Model{
        List<Book> getAvailableBooks(int type);
    }

}
