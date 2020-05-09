package com.test.sherlock.study_menu.move_on_study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.sherlock.R;
import com.test.sherlock.study_menu.move_on_study.chapter.chapter_view;

import java.util.Objects;

public class move_on_study_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);

        initPresenter();
        initAdapterForRV();

    }

    private void initPresenter(){
        this.presenter = new move_on_study_presenter(this);
    }


    private void initAdapterForRV(){
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",-1);
        if(type == -1) finish();
        presenter.getAdapter(this,type);
    }

    @Override
    public void startNextActivity(int type, int chapter) {
        Intent intent = new Intent(this, chapter_view.class);
        intent.putExtra("chapter",chapter);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    @Override
    public void setAdapter(RV_study adapter) {
        RecyclerView rv = findViewById(R.id.test_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.test_rv_divider)));
        rv.addItemDecoration(divider);
        rv.setAdapter(adapter);
    }
}
