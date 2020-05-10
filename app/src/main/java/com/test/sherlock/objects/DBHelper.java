package com.test.sherlock.objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "sh.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = true;
    private Context context;
    private static DBHelper instance;



    private final String sql_get_tasks = "select * from %s;",
            sql_get_answer_by_id = "select * from %s where id = %s;",
            sql_update_status = "update testL set status=%s where id = %s;",
            sql_get_text_of_book_by_chapter = "select * from study where (chapter = %s and type = %s);",
            sql_get_answers_and_questions_test_att = "select * from (select * from answersAtt order by random()) group by ref_q order by random() LIMIT %s;",
            sql_get_chapters_of_book = "select * from %s where (type = %s and title like 'Глава%%');";



    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.context = context;
        instance = this;
    }

    public static DBHelper getInstance(){
        return instance;
    }

    public void updateDataBase(){
        if (mNeedUpdate) {
            Log.d("UPDATE","123");
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();
            copyDataBase();

            mNeedUpdate = false;
        }
    }
    public boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.d("EXIST",dbFile.exists()+"");
        return dbFile.exists();
    }
    public void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    private void copyDBFile() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    @Deprecated
    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            mNeedUpdate = true;
            updateDataBase();
        }

    }


    public Cursor getTasksFromDb(String table){
        SQLiteDatabase sql_db = this.getReadableDatabase();
        Cursor c = sql_db.rawQuery(String.format(sql_get_tasks,table),null);
        c.moveToFirst();
        sql_db.close();
        return c;
    }

    public Cursor getAnswerByID(String table, int id){
        SQLiteDatabase sql_db = this.getReadableDatabase();
        Cursor c = sql_db.rawQuery(String.format(sql_get_answer_by_id,table,id),null);
        c.moveToFirst();
        return c;
    }

    public void updateByID(String table, int id, ContentValues cv) {
        SQLiteDatabase sql_db = this.getWritableDatabase();
        sql_db.update(table,cv,"id = ?",new String[]{String.valueOf(id)});
        sql_db.close();
    }

    public Cursor getStudyBookChapters(String table,int type){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = String.format(sql_get_chapters_of_book,table,type);
        query = query.replace("%%","%");
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        Log.d("COUNT",c.getCount()+"");
        c.moveToFirst();
        return c;
    }

    public Cursor getChapterTextByTypeAndChapter(int type, int chapter){
        SQLiteDatabase sql_db = this.getReadableDatabase();
        Cursor c = sql_db.rawQuery(String.format(sql_get_text_of_book_by_chapter, chapter, type),null);
        c.moveToFirst();
        sql_db.close();
        return c;
    }


    public Cursor getQuestionsAndAnswerForTestAtt(int count, int type){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(String.format(sql_get_answers_and_questions_test_att,count),null);
        c.moveToFirst();
        sqLiteDatabase.close();
        return c;
    }

}