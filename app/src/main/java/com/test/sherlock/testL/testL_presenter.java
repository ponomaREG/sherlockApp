package com.test.sherlock.testL;

import android.content.Context;
import android.view.LayoutInflater;

public class testL_presenter implements Interfaces.Presenter, Interfaces.Presenter.connectionBetweenRecyclerViewAndView, Interfaces.Presenter.connectionBetweenRecyclerViewAndModel {
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
    public void tellViewToShowDialogWithDescAnswer(String desc) {
        view.showDialogWithDescAnswer(desc);
    }

    @Override
    public void getAdapter(Context context) {
        view.setAdapter(new RV_tasks(this,LayoutInflater.from(context),model.getTasks()));
    }

    @Override
    public void tellModelToUpdateInDataBaseStatusByID(int id, int status) {
        model.updateStatusByID(id,status);
    }
}
