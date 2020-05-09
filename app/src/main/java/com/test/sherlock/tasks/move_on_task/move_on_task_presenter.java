package com.test.sherlock.tasks.move_on_task;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class move_on_task_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;

    private String title,text,answer;
    private int status,id;

    move_on_task_presenter(Interfaces.View view){
        this.view = view;
        this.model = new move_on_task_model();
    }

    @Override
    public void OnShowAnswerClick() {
        view.showDialogWithAnswer(answer);
    }

    @Override
    public void OnSetDoneClick() {
        model.updateStatusByID(id);
        view.setStatusDone();
    }

    @Override
    public void setDataAboutTask(String title, String task, String answer, int status, int id) {
        this.answer = answer;
        this.text = task;
        this.title = title;
        this.status = status;
        this.id = id;
    }

    @Override
    public void getImage(Context context) {
        String folder = "images/%s.jpg";
        InputStream stream;

        try {
            stream = context.getAssets().open(String.format(folder,id));
            Drawable drawable = Drawable.createFromStream(stream,null);
            view.setImage(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}