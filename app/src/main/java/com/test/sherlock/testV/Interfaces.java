package com.test.sherlock.testV;

import android.graphics.drawable.Drawable;

import com.test.sherlock.objects.Task;

import java.util.List;

public interface Interfaces {

    interface View{
        void showDialogWithDescriptionAboutTasks();
        void showDialogWithResultOfTest(int result);
        void setImage(Drawable drawable);
        void showQuestionsAndAnswers(String question,String answer1,String answer2,String answer3,String answer4);
        void manipulatingWithVisibilityOfImageView(int visibilty);
        void manipulatingWithVisibilityOfContainerOfQuestionAndAnswers(int visibilty);
        void setNumberOfQuestion(int number_of_question);

    }

    interface Presenter{
        void initSession();
        void OnAnswerClick(android.view.View v);
    }

    interface Model{
        List<Task> getTasks(int type, int count);
    }
}
