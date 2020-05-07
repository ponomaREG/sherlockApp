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
            int answer_id = c.getInt(c.getColumnIndex("answerL"));
            Cursor c_answer = sql_db.rawQuery(String.format(sql_get_answer_by_id,answer_id),null);

            c_answer.close();
            c.moveToNext();
        }

        c.close();

        return null;
    }
}
