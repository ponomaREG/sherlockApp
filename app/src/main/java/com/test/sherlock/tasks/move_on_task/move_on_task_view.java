package com.test.sherlock.tasks.move_on_task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.sherlock.R;
import com.test.sherlock.objects.Adv;

import java.util.Objects;

public class move_on_task_view extends AppCompatActivity implements Interfaces.View {

    private Interfaces.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_on_task_view);

        initPresenter();
        initIntent();
        initOclToButtons();
        initImageOfTask();
    }

    private void initPresenter(){
        this.presenter = new move_on_task_presenter(this);
    }

    private void initIntent(){
        Intent intent = getIntent();
        String task = intent.getStringExtra("text");
        String title = intent.getStringExtra("title");
        String answer = intent.getStringExtra("answer");
        int status = intent.getIntExtra("status",-1);
        int id = intent.getIntExtra("id",-1);
        int position = intent.getIntExtra("position",-1);

        presenter.setDataAboutTask(title,task,answer,status,id,position);

        if(task != null) {
            setText(task);
            setTitle(title);
            if(status == 1){
                setStatusDone();
            }
        }else finish();
    }

    private void initOclToButtons(){
        Button showAnswer = findViewById(R.id.tasks_move_on_task_button_showAnswer);
        Button setDone = findViewById(R.id.tasks_move_on_task_button_setDone);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.tasks_move_on_task_button_showAnswer) presenter.OnShowAnswerClick();
                else presenter.OnSetDoneClick();
            }
        };

        showAnswer.setOnClickListener(ocl);
        setDone.setOnClickListener(ocl);
    }

    private void initImageOfTask(){
        presenter.getImage(this);
    }

    @Override
    public void showDialogWithAnswer(String answer) {
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
    public void setStatusDone() {
        Button setDone = findViewById(R.id.tasks_move_on_task_button_setDone);
        setDone.setEnabled(false);
        setDone.setText("Уже решено");
    }

    private void setTitle(String title) {
        TextView title_v = findViewById(R.id.tasks_move_on_task_title);
        title_v.setText(title);
    }

    private void setText(String text) {
        TextView text_v = findViewById(R.id.tasks_move_on_task_text);
        text_v.setText(text);
    }

    public void setImage(Drawable drawable) {
        ImageView image_v = findViewById(R.id.tasks_move_on_task_image);
        image_v.setImageDrawable(drawable);
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra("position",presenter.getPositionAtParentRV());
        result.putExtra("status",presenter.hasStatusChangedToInt());
        setResult(RESULT_OK,result);
        super.onBackPressed();
    }
}
