package com.test.sherlock.testRP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.sherlock.R;

import java.util.Objects;

public class testRP_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_r_p_view);

        initPresenter();
        initOclToButtonsNextAndShowAnswer();
        initSession();
    }

    private void initPresenter(){
        this.presenter = new testRP_presenter(this);
    }

    private void initOclToButtonsNextAndShowAnswer(){
        ImageView next_iteration = findViewById(R.id.test_rp_button_next);
        ImageView previous_iteration = findViewById(R.id.test_rp_button_previous);
        Button show_answer = findViewById(R.id.test_rp_button_showAnswer);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.test_rp_button_next:
                        presenter.onButtonNextClick();
                        break;
                    case R.id.test_rp_button_previous:
                        presenter.onButtonPreviousClick();
                        break;
                    case R.id.test_rp_button_showAnswer:
                        presenter.onButtonShowAnswerClick();
                        break;
                }
            }
        };

        next_iteration.setOnClickListener(ocl);
        previous_iteration.setOnClickListener(ocl);
        show_answer.setOnClickListener(ocl);

    }

    private void initSession(){
        presenter.initSession();
    }

    @Override
    public void setImage(int id) {
        ImageView imageView = findViewById(R.id.test_rp_image);
        imageView.setImageDrawable(presenter.getImageFromAssets(this,id));
    }

    @Override
    public void setTitle(String title) {
        TextView title_v = findViewById(R.id.test_rp_title);
        Log.d("TOT:E",title);
        title_v.setText(title);
    }

    @Override
    public void showDialogWithAnswer(String answer) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.test_rv_item_desc_dialog);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView)dialog.findViewById(R.id.test_rv_dialog_desc_text)).setText(answer);
        dialog.findViewById(R.id.test_rv_dialog_desc_button_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Deprecated
    public void showFinishDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.test_rv_item_desc_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        TextView desc = dialog.findViewById(R.id.test_rv_dialog_desc_text);
        desc.setText("На этом тест закончен. Советуем время от времени проходить его еще раз , чтобы освежить свои воспоминания о невербалике");
        Button button_dismiss = dialog.findViewById(R.id.test_rv_dialog_desc_button_dismiss);
        button_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void setVisibilityOfPreviousButton(int visibility) {
        ImageView imageView = findViewById(R.id.test_rp_button_previous);
        if(imageView.getVisibility() != visibility) imageView.setVisibility(visibility);
    }

    @Override
    public void setVisibilityOfNextButton(int visibility) {
        ImageView imageView = findViewById(R.id.test_rp_button_next);
        if(imageView.getVisibility() != visibility) imageView.setVisibility(visibility);
    }

}
