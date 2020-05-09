package com.test.sherlock.study_menu;

import android.view.View;

public interface Interfaces {

    interface View{
        void startNextActivity(int type);
    }

    interface Presenter{

        void OnCardClick(android.view.View v);
    }
}
