package com.test.sherlock.tasks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.test.sherlock.R;
import com.test.sherlock.objects.Task;
import com.test.sherlock.tasks.move_on_task.move_on_task_view;

import java.util.Objects;


public class tasks_view extends AppCompatActivity implements Interfaces.View{

    private Interfaces.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);

        initPresenter();
        initRV();
    }

    private void initPresenter(){
        presenter = new tasks_presenter(this);
    }

    private void initRV(){
        presenter.getAdapter(this);
    }

    @Override
    public void setAdapter(RV_tasks adapter) {
        RecyclerView rv = findViewById(R.id.test_rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.test_rv_divider)));
        rv.addItemDecoration(divider);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    public void startNextActivity(Task task) {
        Intent intent = new Intent(this, move_on_task_view.class);
        intent.putExtra("title", task.getTitle());
        intent.putExtra("text", task.getTask());
        intent.putExtra("answer", task.getAnswer());
        intent.putExtra("status", task.getStatus());
        intent.putExtra("id", task.getId());
        intent.putExtra("position",task.getPosition());
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            Log.d("ACTIVITYRESULT","1");
            int result = data.getIntExtra("status", -1);
            int position = data.getIntExtra("position",-1);
            if((result == 1)&&(position != -1)){
                ((Interfaces.Presenter.connectionBetweenRVandView) presenter).tellRVtoSetNewStatusAt(position,1);
            }
        }


    }
}
