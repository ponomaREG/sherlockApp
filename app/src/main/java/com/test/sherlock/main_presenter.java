package com.test.sherlock;

import android.view.View;

import com.test.sherlock.testL.testL_view;

public class main_presenter  implements Interfaces.Presenter{

    private Interfaces.View view;


    main_presenter(main_view view){
        this.view = view;
    }

    @Override
    public void OnSectionClick(View v) {
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
                view.startNextActivity(testL_view.class);
                break;
            case R.id.main_card_testR:
//                        intent = new Intent(main_view.this,act_testR.class);
//                        startActivity(intent);
                break;
        }
    }
}