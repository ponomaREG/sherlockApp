package com.test.sherlock.study_menu;

import android.view.View;

import com.test.sherlock.R;

public class study_menu_presenter implements Interfaces.Presenter{

    private Interfaces.View view;

    study_menu_presenter(Interfaces.View view){
        this.view = view;
    }


    @Override
    public void OnCardClick(View v) {
        switch (v.getId()){
            case R.id.study_menu_book_sh:
                view.startNextActivity(0);
                break;
            case R.id.study_menu_memory:
                view.startNextActivity(1);
                break;
        }
    }
}
