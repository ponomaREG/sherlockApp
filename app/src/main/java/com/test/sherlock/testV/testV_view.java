package com.test.sherlock.testV;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.sherlock.R;

import java.util.Objects;

public class testV_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_v_view);

        initPresenter();
        initOclToButtons();
    }

    private void initPresenter(){
        this.presenter = new testV_presenter(this);
    }

    private void initOclToButtons(){
        Button answer1_v = findViewById(R.id.testV_answer1);
        Button answer2_v = findViewById(R.id.testV_answer2);
        Button answer3_v = findViewById(R.id.testV_answer3);
        Button answer4_v = findViewById(R.id.testV_answer4);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.OnAnswerClick(v);
            }
        };

        answer1_v.setOnClickListener(ocl);
        answer2_v.setOnClickListener(ocl);
        answer3_v.setOnClickListener(ocl);
        answer4_v.setOnClickListener(ocl);
    }


    @Override
    public void showDialogWithDescriptionAboutTasks() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.test_rv_item_desc_dialog);
        dialog.setCancelable(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button button_start = dialog.findViewById(R.id.test_rv_dialog_desc_button_dismiss);
        TextView text = dialog.findViewById(R.id.test_rv_dialog_desc_text);
        text.setText(getResources().getString(R.string.testV_dialog_description));
        text.setTextSize(25);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                presenter.initSession();
            }
        });

        dialog.show();
    }

    @Override
    public void showDialogWithResultOfTest(int result) {
        Dialog  dialog = new Dialog(this);
        dialog.setContentView(R.layout.test_rv_item_desc_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button button_finish = dialog.findViewById(R.id.test_rv_dialog_desc_button_dismiss);
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = dialog.findViewById(R.id.test_rv_dialog_desc_title);
        title.setText("Результаты");

        TextView desc_text = dialog.findViewById(R.id.test_rv_dialog_desc_text);
        desc_text.setText(String.format(getResources().getString(R.string.testV_dialogResult_description),result));

        dialog.show();
    }

    @Override
    public void setImage(Drawable drawable) {
        ImageView image = findViewById(R.id.testV_image);
        image.setImageDrawable(drawable);
    }


    @Override
    public void showQuestionsAndAnswers(String question, String answer1, String answer2, String answer3, String answer4) {
        TextView question_v = findViewById(R.id.testV_question);

        Button answer1_v = findViewById(R.id.testV_answer1);
        Button answer2_v = findViewById(R.id.testV_answer2);
        Button answer3_v = findViewById(R.id.testV_answer3);
        Button answer4_v = findViewById(R.id.testV_answer4);

        question_v.setText(question);

        answer1_v.setText(answer1);
        answer2_v.setText(answer2);
        answer3_v.setText(answer3);
        answer4_v.setText(answer4);
    }

    @Override
    public void manipulatingWithVisibilityOfImageView(int visibilty) {
        ImageView imageView = findViewById(R.id.testV_image);
        if((visibilty == imageView.getVisibility())) return;
        imageView.setVisibility(visibilty);
    }

    @Override
    public void manipulatingWithVisibilityOfContainerOfQuestionAndAnswers(int visibilty) {
        RelativeLayout rl = findViewById(R.id.testV_containerWithQuestionAndAnswers);
        if((visibilty == rl.getVisibility())) return;
        rl.setVisibility(visibilty);
    }

    @Override
    public void setNumberOfQuestion(int number_of_question) {
        TextView title_number_of_question = findViewById(R.id.testV_title_numberOfQuestion);
        title_number_of_question.setText(String.valueOf(number_of_question));
    }
}
