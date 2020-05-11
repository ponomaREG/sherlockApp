package com.test.sherlock.study_menu;

public interface Interfaces {

    interface View{
        void startNextActivity(int type);
    }

    interface Presenter{

        void OnCardClick(android.view.View v);
    }
}
