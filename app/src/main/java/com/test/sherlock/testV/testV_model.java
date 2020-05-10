package com.test.sherlock.testV;

import android.database.Cursor;

import com.test.sherlock.objects.DBHelper;
import com.test.sherlock.objects.Task;

import java.util.ArrayList;
import java.util.List;

public class testV_model implements Interfaces.Model{

    private DBHelper dbHelper = DBHelper.getInstance();


    @Override
    public List<Task> getTasks(int type, int count) {
        List<Task> tasks = new ArrayList<>();
        Cursor c = dbHelper.getQuestionsAndAnswerForTestAtt(count,type);
        for(int i = 0;i<c.getCount();i++){
            Task new_task = new Task();

            String question = c.getString(c.getColumnIndex("question"));
            String answer1 = c.getString(c.getColumnIndex("answer1"));
            String answer2 = c.getString(c.getColumnIndex("answer2"));
            String answer3 = c.getString(c.getColumnIndex("answer3"));
            String answer4 = c.getString(c.getColumnIndex("answer4"));
            String correctAnswer = c.getString(c.getColumnIndex("correctAnswer"));
            int ref_q = c.getInt(c.getColumnIndex("ref_q"));

            new_task.setTask(question)
                    .setAnswer1(answer1)
                    .setAnswer2(answer2)
                    .setAnswer3(answer3)
                    .setAnswer4(answer4)
                    .setCorrect_answer(correctAnswer)
                    .setId(ref_q);

            tasks.add(new_task);
            c.moveToNext();
        }
        c.close();
        return tasks;
    }
}
