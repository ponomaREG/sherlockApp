package com.test.sherlock.study_menu.move_on_study;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

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
        prepareView();
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

    private void prepareView(){
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        ImageView image_logo_in_actionbar = new ImageView(actionBar.getThemedContext());
        image_logo_in_actionbar.setScaleType(ImageView.ScaleType.FIT_XY);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                120,
                120,
                Gravity.CENTER
        );
        image_logo_in_actionbar.setLayoutParams(lp);
        image_logo_in_actionbar.setImageDrawable(getDrawable(R.drawable.study_book_icon));
        actionBar.setCustomView(image_logo_in_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);
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
