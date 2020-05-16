package com.test.sherlock.study_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
