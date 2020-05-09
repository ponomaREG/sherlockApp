package com.test.sherlock.study_menu.move_on_study.chapter;

import android.database.Cursor;

import com.test.sherlock.objects.Book;
import com.test.sherlock.objects.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class chapter_model implements Interfaces.Model {

    private DBHelper dbhelper = DBHelper.getInstance();

    @Override
    public List<Book> getChapterTextByTypeAndChapter(int type, int chapter) {
        List<Book> books = new ArrayList<>();
        Cursor c = dbhelper.getChapterTextByTypeAndChapter(type,chapter);
        for(int i =0;i<c.getCount();i++){
            Book new_book = new Book();

            String title = null;
            String text;

            if(!c.isNull(c.getColumnIndex("title"))) title = c.getString(c.getColumnIndex("title"));
            text = c.getString(c.getColumnIndex("text"));

            new_book.setType(type);
            new_book.setChapter(chapter);
            new_book.setTitle(title);
            new_book.setText(text);
            c.moveToNext();
            books.add(new_book);
        }
        c.close();
        return books;
    }
}
