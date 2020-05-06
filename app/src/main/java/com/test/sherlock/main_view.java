package com.test.sherlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class main_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initOclToCardsMenu();
    }


    private void initOclToCardsMenu(){
        RelativeLayout testR, testL, testD, tasks, study;

        testD = findViewById(R.id.main_card_testD);
        testL = findViewById(R.id.main_card_testL);
        testR = findViewById(R.id.main_card_testR);
        tasks = findViewById(R.id.main_card_tasks);
        study = findViewById(R.id.main_card_study);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (v.getId()){
                    case R.id.main_card_study:
//                        intent = new Intent(main_view.this,act_study.class);
//                        startActivity(intent);
                        break;
                    case R.id.main_card_tasks:
//                        intent = new Intent(main_view.this,act_tasks.class);
//                        startActivity(intent);
                        break;
                    case R.id.main_card_testD:
//                        intent = new Intent(main_view.this,act_testD.class);
//                        startActivity(intent);
                        break;
                    case R.id.main_card_testL:
//                        intent = new Intent(main_view.this,act_testL.class);
//                        startActivity(intent);
                        break;
                    case R.id.main_card_testR:
//                        intent = new Intent(main_view.this,act_testR.class);
//                        startActivity(intent);
                        break;
                }
            }
        };
        study.setOnClickListener(ocl);
        tasks.setOnClickListener(ocl);
        testD.setOnClickListener(ocl);
        testL.setOnClickListener(ocl);
        testR.setOnClickListener(ocl);
    }
}
