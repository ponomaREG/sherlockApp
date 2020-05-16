package com.test.sherlock.study_menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.test.sherlock.R;
import com.test.sherlock.objects.Adv;
import com.test.sherlock.study_menu.move_on_study.move_on_study_view;

public class study_menu_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_menu_view);

        initPresenter();
        initOclToCards();
        prepareView();
    }


    private void initPresenter(){
        presenter = new study_menu_presenter(this);
    }

    private void initOclToCards(){
        LinearLayout rl_book = findViewById(R.id.study_menu_book_sh);
        LinearLayout rl_memory = findViewById(R.id.study_menu_memory);

        View.OnClickListener ocl = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                presenter.OnCardClick(v);
            }
        };

        rl_book.setOnClickListener(ocl);
        rl_memory.setOnClickListener(ocl);
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
        image_logo_in_actionbar.setImageDrawable(getDrawable(R.drawable.main_card_study_icon));
        actionBar.setCustomView(image_logo_in_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public void startNextActivity(int type) {
        Intent intent = new Intent(this, move_on_study_view.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Adv.iterationAd();
    }
}
