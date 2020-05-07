package com.test.sherlock.testL;

import android.content.Context;

import com.test.sherlock.objects.Task;

import java.util.List;

public interface Interfaces {


    interface View{
        void showToastCorrectAnswer();
        void showToastIncorrectAnswer();
        void showToastErrorRadioButtonDoesNotChecked();
        void showDialogWithDescAnswer(String answer);
        void setAdapter(RV_tasks adapter);
    }

    interface Presenter{
        void getAdapter(Context context);

        interface connectionBetweenRecyclerViewAndView{
            void tellViewToShowMessageThatUserFoundCorrectAnswer();
            void tellViewToShowMessageThatUserFoundIncorrectAnswer();
            void tellViewToShowMessageThatUserDoesNotChooseAnswer();
            void tellViewToShowDialogWithDescAnswer(String desc);
        }
    }

    interface Model{
        List<Task> getTasks();
    }
}
