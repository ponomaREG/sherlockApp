package com.test.sherlock.tasks.move_on_task;

import android.content.ContentValues;

import com.test.sherlock.objects.DBHelper;

public class move_on_task_model implements Interfaces.Model {

    private DBHelper dbHelper = DBHelper.getInstance();

//TODO: ОБновление статуса
    @Override
    public void updateStatusByID(int id) {
        ContentValues cv = new ContentValues();
        cv.put("is_done",1);
        dbHelper.updateByID("tasks",id,cv);
    }
}
