package com.test.sherlock.testRP;

import android.database.Cursor;

import com.test.sherlock.objects.DBHelper;
import com.test.sherlock.objects.Task;

import java.util.ArrayList;
import java.util.List;

public class testRP_model  implements Interfaces.Model{

    private DBHelper dbHelper = DBHelper.getInstance();

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Cursor c = dbHelper.getTasksFromDb("testRP");
        for(int i = 0;i<c.getCount();i++){
            Task new_task = new Task();

            String title = c.getString(c.getColumnIndex("title"));
            String answer = c.getString(c.getColumnIndex("answer"));
            int id = c.getInt(c.getColumnIndex("id"));

            new_task.setCorrect_answer(answer)
                    .setTitle(title)
                    .setId(id);

            tasks.add(new_task);
            c.moveToNext();
        }
        c.close();
        return tasks;
    }
}
