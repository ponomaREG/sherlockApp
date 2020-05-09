package com.test.sherlock.study_menu.move_on_study;

import android.database.Cursor;

import com.test.sherlock.objects.Book;
import com.test.sherlock.objects.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class move_on_study_model implements Interfaces.Model {


    private DBHelper dbHelper = DBHelper.getInstance();

    @Override
    public List<Book> getAvailableBooks(int type) {
        List<Book> books = new ArrayList<>();
        Cursor c = dbHelper.getStudyBookChapters("study",type);
        for(int i = 0;i<c.getCount();i++){
            Book new_chapter = new Book();

            String title = c.getString(c.getColumnIndex("title"));
            int chapter = c.getInt(c.getColumnIndex("chapter"));

            new_chapter.setTitle(title);
            new_chapter.setChapter(chapter);
            new_chapter.setType(type);

            books.add(new_chapter);

            c.moveToNext();
        }
        c.close();
        return books;
    }
}
