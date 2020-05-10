package com.test.sherlock.study_menu.move_on_study.chapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.sherlock.R;
import com.test.sherlock.objects.Book;

import java.util.List;

public class chapter_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;

    chapter_presenter(Interfaces.View view){
        this.view = view;
        this.model = new chapter_model();
    }

    @Override
    public void getViews(LayoutInflater inflater, int type, int chapter, ViewGroup parent) {
        List<Book> books = model.getChapterTextByTypeAndChapter(type,chapter);

        for(int i = 0;i<books.size();i++){
            Book current_book = books.get(i);
            if(i == 0) view.setMainTitle(current_book.getTitle());
            else{
                String title = current_book.getTitle();
                if(title!=null) {
                    TextView textView_chapter_title = (TextView) inflater.inflate(R.layout.chapter_chapter_title, parent, false);
                    textView_chapter_title.setText(title);
                    view.addViewInContainer(textView_chapter_title);
                }
            }
            TextView textView_chapter_text = (TextView) inflater.inflate(R.layout.chapter_text,parent,false);
            textView_chapter_text.setText(current_book.getText());
            view.addViewInContainer(textView_chapter_text);
        }

    }
}
