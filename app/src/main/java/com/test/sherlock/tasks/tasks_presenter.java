package com.test.sherlock.tasks;

import android.content.Context;
import android.view.LayoutInflater;

import com.test.sherlock.objects.Task;

public class tasks_presenter implements Interfaces.Presenter,Interfaces.Presenter.connectionBetweenRVandView{

    private Interfaces.View view;
    private Interfaces.Model model;

    tasks_presenter(Interfaces.View view){
        this.view = view;
        this.model = new tasks_model();
    }


    @Override
    public void getAdapter(Context context) {
        view.setAdapter(new RV_tasks(this, LayoutInflater.from(context),model.getTasks()));
    }

    @Override
    public void tellViewToStartNextActivity(Task task) {
        view.startNextActivity(task);
    }
}
