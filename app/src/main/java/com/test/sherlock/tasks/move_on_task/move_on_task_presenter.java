package com.test.sherlock.tasks.move_on_task;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.test.sherlock.R;
import com.test.sherlock.objects.Adv;

import java.io.IOException;
import java.io.InputStream;

public class move_on_task_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;

    private String answer;
    private int id;
    private int position;
    private boolean hasStatusChanged = false;

    move_on_task_presenter(Interfaces.View view){
        this.view = view;
        this.model = new move_on_task_model();


    }

    @Override
    public void OnShowAnswerClick() {
        Adv.showAdWithLoadedWith33PercentChance();
        view.showDialogWithAnswer(answer);
    }

    @Override
    public void OnSetDoneClick() {
        model.updateStatusByID(id);
        view.setStatusDone();
        hasStatusChanged = true;
    }

    @Override
    public void setDataAboutTask(String title, String task, String answer, int status, int id, int position) {
        this.answer = answer;
        this.id = id;
        this.position = position;
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
            view.setImage(context.getDrawable(R.drawable.error_image));
        }

    }

    @Override
    public int hasStatusChangedToInt() {
        int result = 0;
        if(this.hasStatusChanged) result = 1;
        return result;
    }

    @Override
    public int getPositionAtParentRV() {
        return this.position;
    }

}
