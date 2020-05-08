package com.test.sherlock.tasks;

import android.content.Context;

import com.test.sherlock.objects.Task;

import java.util.List;

public interface Interfaces {

    interface View{
        void setAdapter(RV_tasks adapter);
        void startNextActivity(Task task);
    }

    interface Presenter{
        void getAdapter(Context context);
        interface connectionBetweenRVandView{
            void tellViewToStartNextActivity(Task task);
        }
    }

    interface Model{
        List<Task> getTasks();
    }
}
