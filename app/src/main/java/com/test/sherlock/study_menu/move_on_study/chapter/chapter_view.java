package com.test.sherlock.study_menu.move_on_study.chapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.sherlock.R;

public class chapter_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_view);

        initPresenter();
        initViews();
    }


    private void initPresenter(){
        this.presenter = new chapter_presenter(this);
    }

    private void initViews(){
        LinearLayout container = findViewById(R.id.move_on_task_chapter_container);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",-1);
        int chapter = intent.getIntExtra("chapter",-1);
        if((type == -1)||(chapter ==-1)) finish();
        presenter.getViews(LayoutInflater.from(this), type, chapter, container);
    }

    @Override
    public void setMainTitle(String main_title) {
        TextView main_title_v = findViewById(R.id.move_on_task_chapter_main_title);
        main_title_v.setText(main_title);
    }

    @Override
    public void addViewInContainer(View v) {
        LinearLayout container = findViewById(R.id.move_on_task_chapter_container);
        container.addView(v);
    }
}
