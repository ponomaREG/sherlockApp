package com.test.sherlock.tasks;

import android.content.ContentValues;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sherlock.R;
import com.test.sherlock.objects.Task;


import java.util.List;

public class RV_tasks extends RecyclerView.Adapter<RV_tasks.Holder> {

    private Interfaces.Presenter.connectionBetweenRVandView presenter;
    private LayoutInflater inflater;
    private List<Task> tasks;

    RV_tasks(Interfaces.Presenter presenter, LayoutInflater inflater, List<Task> tasks){
        this.presenter = (Interfaces.Presenter.connectionBetweenRVandView) presenter;
        this.inflater = inflater;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.test_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Task current_task = tasks.get(position);

        holder.title.setText(current_task.getTitle());
        holder.showAnswer.setVisibility(View.INVISIBLE);
        holder.setDone.setVisibility(View.INVISIBLE);
        if(current_task.getStatus() == 0) {
            holder.status.setVisibility(View.GONE);
        }
        else {
            holder.status.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.tellViewToStartNextActivity(current_task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        TextView title, status;
        ImageView setDone,showAnswer;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.test_rv_item_task);
            showAnswer = itemView.findViewById(R.id.test_rv_item_showAnswer);
            setDone = itemView.findViewById(R.id.test_rv_item_setDone);
            status = itemView.findViewById(R.id.test_rv_item_status);
        }
    }
}
