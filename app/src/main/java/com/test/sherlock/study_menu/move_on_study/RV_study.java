package com.test.sherlock.study_menu.move_on_study;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sherlock.R;
import com.test.sherlock.objects.Book;

import java.util.List;

public class RV_study extends RecyclerView.Adapter<RV_study.Holder> {

    private Interfaces.Presenter.connectionBetweenRVandView presenter;
    private LayoutInflater inflater;
    private List<Book> books;

    RV_study(Interfaces.Presenter presenter, LayoutInflater inflater, List<Book> books){
        this.presenter = (Interfaces.Presenter.connectionBetweenRVandView) presenter;
        this.inflater = inflater;
        this.books = books;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.test_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Book current_book = books.get(position);

        holder.title.setText(current_book.getTitle());
        holder.showAnswer.setVisibility(View.INVISIBLE);
        holder.setDone.setVisibility(View.INVISIBLE);
        holder.status.setVisibility(View.INVISIBLE);
//        if(current_book.getStatus() == 0) {
//            holder.status.setVisibility(View.GONE);
//        }
//        else {
//            holder.status.setVisibility(View.VISIBLE);
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.tellViewToStartNextActivity(current_book.getType(),current_book.getChapter());
            }
        });

        holder.containerNumberOfQuestion.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class Holder extends RecyclerView.ViewHolder{
        LinearLayout containerNumberOfQuestion;
        TextView title;
        ImageView setDone,showAnswer, status;
        Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.test_rv_item_task);
            showAnswer = itemView.findViewById(R.id.test_rv_item_showAnswer);
            setDone = itemView.findViewById(R.id.test_rv_item_setDone);
            status = itemView.findViewById(R.id.test_rv_item_status);
            containerNumberOfQuestion = itemView.findViewById(R.id.test_rv_item_numberOfTask_container);
        }
    }
}
