package com.test.sherlock.testL;

import android.content.Context;
import android.view.LayoutInflater;

public class testL_presenter implements Interfaces.Presenter, Interfaces.Presenter.connectionBetweenRecyclerViewAndView {
    private Interfaces.View view;
    private Interfaces.Model model;


    testL_presenter(testL_view view){
        this.view = view;
        this.model = new testL_model();
    }
    @Override
    public void tellViewToShowMessageThatUserFoundCorrectAnswer() {
        view.showToastCorrectAnswer();
    }

    @Override
    public void tellViewToShowMessageThatUserFoundIncorrectAnswer() {
        view.showToastIncorrectAnswer();
    }

    @Override
    public void tellViewToShowMessageThatUserDoesNotChooseAnswer() {
        view.showToastErrorRadioButtonDoesNotChecked();
    }

    @Override
    public void getAdapter(Context context) {
        view.setAdapter(new RV_tasks(LayoutInflater.from(context),model.getTasks()));
    }
}
