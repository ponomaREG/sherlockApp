package com.test.sherlock.study_menu.move_on_study;

import android.content.Context;
import android.view.LayoutInflater;

public class move_on_study_presenter implements Interfaces.Presenter, Interfaces.Presenter.connectionBetweenRVandView{

    private Interfaces.Model model;
    private Interfaces.View view;

    move_on_study_presenter(Interfaces.View view){
        this.model = new move_on_study_model();
        this.view = view;
    }

    @Override
    public void getAdapter(Context context, int type) {
        RV_study rv_study = new RV_study(this, LayoutInflater.from(context),model.getAvailableBooks(type));
        view.setAdapter(rv_study);
    }

    @Override
    public void OnChapterClick(android.view.View v) {

    }

    @Override
    public void tellViewToStartNextActivity(int type, int chapter) {
        view.startNextActivity(type, chapter);
    }
}
