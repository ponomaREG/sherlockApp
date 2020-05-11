package com.test.sherlock.tasks;

import android.content.Context;
import android.view.LayoutInflater;

import com.test.sherlock.objects.Task;

public class tasks_presenter implements Interfaces.Presenter,Interfaces.Presenter.connectionBetweenRVandView{

    private Interfaces.View view;
    private Interfaces.Model model;

    private RV_tasks adapter;

    tasks_presenter(Interfaces.View view){
        this.view = view;
        this.model = new tasks_model();
    }


    @Override
    public void getAdapter(Context context) {
        this.adapter = new RV_tasks(this, LayoutInflater.from(context),model.getTasks());
        view.setAdapter(this.adapter);
    }

    @Override
    public void tellViewToStartNextActivity(Task task) {
        view.startNextActivity(task);
    }

    @Override
    public void tellRVtoSetNewStatusAt(int position, int status) {
        this.adapter.setNewStatusToTaskAt(position,status);
        this.adapter.notifyItemChanged(position);
    }
}
