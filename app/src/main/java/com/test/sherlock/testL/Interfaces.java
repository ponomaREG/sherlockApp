package com.test.sherlock.testL;

import android.content.Context;

import com.test.sherlock.objects.Task;

import java.util.List;

public interface Interfaces {


    interface View{
        void showToastCorrectAnswer();
        void showToastIncorrectAnswer();
        void showToastErrorRadioButtonDoesNotChecked();
        void setAdapter(RV_tasks adapter);
    }

    interface Presenter{
        void getAdapter(Context context);

        interface connectionBetweenRecyclerViewAndView{
            void tellViewToShowMessageThatUserFoundCorrectAnswer();
            void tellViewToShowMessageThatUserFoundIncorrectAnswer();
            void tellViewToShowMessageThatUserDoesNotChooseAnswer();
        }
    }

    interface Model{
        List<Task> getTasks();
    }
}
