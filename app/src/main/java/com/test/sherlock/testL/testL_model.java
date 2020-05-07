package com.test.sherlock.testL;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.sherlock.objects.DBHelper;
import com.test.sherlock.objects.Task;

import java.util.ArrayList;
import java.util.List;

public class testL_model implements Interfaces.Model {

    private DBHelper dbHelper;

    private final String sql_get_tasks = "select * from testL;", sql_get_answer_by_id = "select * from answerL where id = %s;";

    testL_model(){
        this.dbHelper = DBHelper.getInstance();
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase sql_db = dbHelper.getReadableDatabase();
        Cursor c = sql_db.rawQuery(sql_get_tasks,null);
        c.moveToFirst();
        for(int i = 0;i<c.getCount();i++){
            int answer_id = c.getInt(c.getColumnIndex("answersL"));
            int type_task = c.getInt(c.getColumnIndex("type"));
            String task = c.getString(c.getColumnIndex("text"));
            Task new_task = new Task();
            new_task.setTask(task);
            new_task.setType_task(type_task);

            Cursor c_answer = sql_db.rawQuery(String.format(sql_get_answer_by_id,answer_id),null);
            c_answer.moveToFirst();
            String desc_answer = c_answer.getString(c_answer.getColumnIndex("answer"));
            if(type_task == 1){
                String answer1 = c_answer.getString(c_answer.getColumnIndex("answer1"));
                String answer2 = c_answer.getString(c_answer.getColumnIndex("answer2"));
                String answer3 = c_answer.getString(c_answer.getColumnIndex("answer3"));
                String answer4 = c_answer.getString(c_answer.getColumnIndex("answer4"));
                String answer5 = c_answer.getString(c_answer.getColumnIndex("answer5"));
                String correct_answer = c_answer.getString(c_answer.getColumnIndex("correct"));

                new_task.setAnswer1(answer1);
                new_task.setAnswer2(answer2);
                new_task.setAnswer3(answer3);
                new_task.setAnswer4(answer4);
                new_task.setAnswer5(answer5);
                new_task.setCorrect_answer(correct_answer);
            }

            new_task.setAnswer(desc_answer);
            tasks.add(new_task);
            c_answer.close();
            c.moveToNext();
        }
        c.close();

        return tasks;
    }
}
