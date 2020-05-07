package com.test.sherlock.testL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.test.sherlock.R;

public class testL_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_l_view);

        initPresenter();
        initRV();
    }

    private void initPresenter(){
        this.presenter = new testL_presenter(this);
    }

    private void initRV(){
        presenter.getAdapter(this);
    }


    @Override
    public void showToastCorrectAnswer() {
        Toast.makeText(this,"Правильно!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastIncorrectAnswer() {
        Toast.makeText(this, "К сожалению , неправильно", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastErrorRadioButtonDoesNotChecked() {
        Toast.makeText(this, "Необходимо выбрать ответ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(RV_tasks adapter) {
        RecyclerView rv = findViewById(R.id.test_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
