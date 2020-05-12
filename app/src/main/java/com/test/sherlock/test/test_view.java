package com.test.sherlock.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.sherlock.R;
import com.test.sherlock.objects.Adv;

import java.util.Objects;

public class test_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);

        initPresenter();
        initRV();
    }

    private void initPresenter(){
        this.presenter = new test_presenter(this);
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
    public void showDialogWithDescAnswer(String answer) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.test_rv_item_desc_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView) dialog.findViewById(R.id.test_rv_dialog_desc_text)).setText(answer);
        dialog.findViewById(R.id.test_rv_dialog_desc_button_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void setAdapter(RV_tasks adapter) {
        RecyclerView rv = findViewById(R.id.test_rv);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.test_rv_divider)));
        rv.addItemDecoration(itemDecorator);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Adv.iterationAd();
    }
}
