package com.test.sherlock.testRP;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.test.sherlock.objects.Task;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testRP_presenter implements Interfaces.Presenter{

    private Interfaces.View view;
    private Interfaces.Model model;
    private List<Task> tasks;
    private int current_task = -1;


    testRP_presenter(Interfaces.View view){
        this.view = view;
        this.model = new testRP_model();
    }

    @Override
    public void initSession() {
        this.tasks = model.getTasks();
        nextIteration();
    }

    private void nextIteration() {
        this.current_task++;
        if(this.current_task == 1) view.setVisibilityOfPreviousButton(View.VISIBLE);
        if(this.current_task == tasks.size()-1) {
            view.setVisibilityOfNextButton(View.GONE);
        }
        Task current_task = tasks.get(this.current_task);
        view.setImage(current_task.getId());
        view.setTitle(current_task.getTitle());

    }

    private void previousIteration(){
        this.current_task--;
        if(this.current_task == tasks.size() - 2)view.setVisibilityOfNextButton(View.VISIBLE);
        if(this.current_task == 0) {
            view.setVisibilityOfPreviousButton(View.GONE);
//            this.current_task++;
        }
        Task current_task = tasks.get(this.current_task);
        view.setImage(current_task.getId());
        view.setTitle(current_task.getTitle());

    }

    @Override
    public void onButtonShowAnswerClick() {
        Task current_task = tasks.get(this.current_task);
        view.showDialogWithAnswer(current_task.getCorrect_answer());
    }

    @Override
    public void onButtonNextClick() {
        nextIteration();
    }

    @Override
    public void onButtonPreviousClick() {
        previousIteration();
    }


    @Override
    public Drawable getImageFromAssets(Context context, int id) {
        InputStream is;
        String folder = "imagesRP/%s.png";
        try {
            is = context.getAssets().open(String.format(folder,id));
            return Drawable.createFromStream(is,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
