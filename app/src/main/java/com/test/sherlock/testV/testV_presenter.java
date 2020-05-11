package com.test.sherlock.testV;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.sherlock.R;
import com.test.sherlock.objects.Task;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testV_presenter implements Interfaces.Presenter{

    private Interfaces.View view;
    private Interfaces.Model model;

    private List<Task> tasks;
    private Timer timer;
    private Context context;
    private int current_number_of_task = 0, result = 0;

    testV_presenter(testV_view view){
        this.view = view;
        this.model = new testV_model();
        this.context = view;

        view.showDialogWithDescriptionAboutTasks();
    }


    @Override
    public void initSession() {
        this.tasks = model.getTasks(0, 5);
        this.timer = new Timer(2000,1000);
        nextIteration();
    }

    @Override
    public void OnAnswerClick(android.view.View v) {
        Task current_task = tasks.get(this.current_number_of_task);
        String answer = ((Button) v).getText().toString();
        if(current_task.getCorrect_answer().equals(answer)) this.result++;
        this.current_number_of_task++;
        nextQuestionIteration();
    }

    private void nextIteration(){
        if(this.current_number_of_task<tasks.size()) {
            Task current_task = tasks.get(this.current_number_of_task);
            view.setImage(getImageDrawable(current_task.getId()));
            this.current_number_of_task++;
            this.timer.start();
        }else{
            this.current_number_of_task = 0;
            view.manipulatingWithVisibilityOfImageView(View.INVISIBLE);
            view.manipulatingWithVisibilityOfContainerOfQuestionAndAnswers(View.VISIBLE);
            nextQuestionIteration();
        }
    }

    private void nextQuestionIteration(){
        if(this.current_number_of_task<tasks.size()) {
            Task current_task = tasks.get(this.current_number_of_task);
            view.setNumberOfQuestion(this.current_number_of_task + 1);
            view.showQuestionsAndAnswers(
                    current_task.getTask(), current_task.getAnswer1(),
                    current_task.getAnswer2(), current_task.getAnswer3(),
                    current_task.getAnswer4());
        }else view.showDialogWithResultOfTest(this.result);
    }

    private Drawable getImageDrawable(int id_of_task){
        String folder = "imagesAtt/%s.jpg";
        InputStream is;
        try {
            is = context.getAssets().open(String.format(folder,id_of_task));
            return Drawable.createFromStream(is,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return context.getDrawable(R.drawable.error_image);
         }

    class Timer extends CountDownTimer{

        Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("TIK","TAC");
        }

        @Override
        public void onFinish() {
            nextIteration();
        }
    }
}
