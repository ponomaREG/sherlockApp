package com.test.sherlock;

import android.view.View;

import com.test.sherlock.study_menu.study_menu_view;
import com.test.sherlock.tasks.tasks_view;
import com.test.sherlock.test.test_view;
import com.test.sherlock.testRP.testRP_view;
import com.test.sherlock.testV.testV_view;

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
                view.startNextActivity(study_menu_view.class);
                break;
            case R.id.main_card_tasks:
//                        intent = new Intent(main_view.this,act_tasks.class);
//                        startActivity(intent);
                view.startNextActivity(tasks_view.class);
                break;
            case R.id.main_card_testD:
//                        intent = new Intent(main_view.this,act_testD.class);
//                        startActivity(intent);
                view.startNextActivity(testV_view.class);
                break;
            case R.id.main_card_testL:
//                        intent = new Intent(main_view.this,act_testL.class);
//                        startActivity(intent);
                view.startNextActivity(test_view.class);
                break;
            case R.id.main_card_testR:
//                        intent = new Intent(main_view.this,act_testR.class);
//                        startActivity(intent);
                view.startNextActivity(testRP_view.class);
                break;
        }
    }
}
