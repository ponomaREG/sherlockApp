package com.test.sherlock.tasks;

import android.database.Cursor;
import android.util.Log;

import com.test.sherlock.objects.DBHelper;
import com.test.sherlock.objects.Task;

import java.util.ArrayList;
import java.util.List;

public class tasks_model implements Interfaces.Model {

    private DBHelper dbHelper = DBHelper.getInstance();

    @Override
    public List<Task> getTasks() {
        List<Task> tasks =new ArrayList<>();
        Cursor c = dbHelper.getTasksFromDb("tasks");
        for(int i = 0;i<c.getCount();i++){
            int answer_id = c.getInt(c.getColumnIndex("answers"));
            int type_task = c.getInt(c.getColumnIndex("type"));
            int id = c.getInt(c.getColumnIndex("id"));
            int status = c.getInt(c.getColumnIndex("is_done"));
            String task = c.getString(c.getColumnIndex("text"));
            String title = c.getString(c.getColumnIndex("title"));

            Task new_task = new Task();
            new_task.setTask(task);
            new_task.setType_task(type_task);
            new_task.setId(id);
            new_task.setStatus(status);
            new_task.setTitle(title);

            Cursor c_answer = dbHelper.getAnswerByID("answers",answer_id);
            String desc_answer = c_answer.getString(c_answer.getColumnIndex("answer"));
            new_task.setAnswer(desc_answer);
            tasks.add(new_task);
            c_answer.close();
            c.moveToNext();
        }
        c.close();
        return tasks;
    }
}
