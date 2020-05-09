package com.test.sherlock.tasks.move_on_task;

import android.content.Context;
import android.graphics.drawable.Drawable;

public interface Interfaces {


    interface View{
        void showDialogWithAnswer(String answer);
        void setStatusDone();
        void setImage(Drawable drawable);
    }

    interface Presenter{
        void OnShowAnswerClick();
        void OnSetDoneClick();
        void setDataAboutTask(String title, String task, String answer, int status , int id);
        void getImage(Context context);
    }

    interface Model{
        void updateStatusByID(int id);
    }

}