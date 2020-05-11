package com.test.sherlock.testRP;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.test.sherlock.objects.Task;

import java.util.List;

public interface Interfaces {

    interface View{
        void setImage(int id);
        void setTitle(String title);
        void showDialogWithAnswer(String answer);
//        void showFinishDialog();
        void setVisibilityOfPreviousButton(int visibilty);
        void setVisibilityOfNextButton(int visibility);
    }

    interface Presenter{
        void initSession();
        void onButtonShowAnswerClick();
        void onButtonNextClick();
        void onButtonPreviousClick();
        Drawable getImageFromAssets(Context context,int id);
    }

    interface Model{
        List<Task> getTasks();
    }
}
