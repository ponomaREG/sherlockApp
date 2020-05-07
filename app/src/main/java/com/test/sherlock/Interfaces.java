package com.test.sherlock;

public interface Interfaces {


    interface View{
        void startNextActivity(Class cls);
    }

    interface Presenter{
        void OnSectionClick(android.view.View v);
    }

    interface Model{

    }
}
