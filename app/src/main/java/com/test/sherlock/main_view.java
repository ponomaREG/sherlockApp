package com.test.sherlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.test.sherlock.objects.Adv;
import com.test.sherlock.objects.DBHelper;

public class main_view extends AppCompatActivity implements Interfaces.View{

    private main_presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initPresenter();
        initMobileAds();
        initOclToCardsMenu();
        initInstanceOfDB();
    }


    private void initPresenter(){
        this.presenter = new main_presenter(this);
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
                presenter.OnSectionClick(v);
            }
        };

        study.setOnClickListener(ocl);
        tasks.setOnClickListener(ocl);
        testD.setOnClickListener(ocl);
        testL.setOnClickListener(ocl);
        testR.setOnClickListener(ocl);
    }

    void initInstanceOfDB(){
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.copyDataBase();
//        dbHelper.updateDataBase();


    }

    @Override
    public void startNextActivity(Class cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    private void initMobileAds(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        Adv.initInstanceOfAds(this);
    }

}
