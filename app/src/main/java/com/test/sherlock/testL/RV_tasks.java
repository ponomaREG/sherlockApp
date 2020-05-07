package com.test.sherlock.testL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.sherlock.R;
import com.test.sherlock.objects.Task;

import java.util.List;

public class RV_tasks extends RecyclerView.Adapter<RV_tasks.ViewHolder>{

    private LayoutInflater inflater;
    private List<Task> tasks;
    private Interfaces.Presenter.connectionBetweenRecyclerViewAndView presenter;

    RV_tasks(Interfaces.Presenter.connectionBetweenRecyclerViewAndView presenter, LayoutInflater inflater, List<Task> tasks){
        this.inflater = inflater;
        this.tasks = tasks;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.test_l_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            holder.setIsRecyclable(false);
            final Task current_task = tasks.get(position);
            int task_type = current_task.getType_task();
            int status = current_task.getStatus();

            //Установить текст задания
            holder.task.setText(current_task.getTask());

            //Установить статус: решена ли текущая задача или нет
            if(status == 1) {
                holder.status.setVisibility(View.VISIBLE);
                holder.set_done.setVisibility(View.GONE);
            }
            else holder.status.setVisibility(View.GONE);

            if(task_type == 1) {
                holder.set_done.setVisibility(View.GONE);
                holder.answers_block.setVisibility(View.VISIBLE);

                holder.answer1_text.setText(current_task.getAnswer1());
                holder.answer2_text.setText(current_task.getAnswer2());
                holder.answer3_text.setText(current_task.getAnswer3());
                holder.answer4_text.setText(current_task.getAnswer4());
                holder.answer5_text.setText(current_task.getAnswer5());

                holder.check_answer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null == holder.current_checked_button) {
                            presenter.tellViewToShowMessageThatUserDoesNotChooseAnswer();
                        }
                        else{
                            int checked_radio_button_id = holder.current_checked_button.getId();
                            String answer = null;
                            switch (checked_radio_button_id){
                                case R.id.test_rv_item_radio_answer1:
                                    answer = holder.answer1_text.getText().toString();
                                    break;
                                case R.id.test_rv_item_radio_answer2:
                                    answer = holder.answer2_text.getText().toString();
                                    break;
                                case R.id.test_rv_item_radio_answer3:
                                    answer = holder.answer3_text.getText().toString();
                                    break;
                                case R.id.test_rv_item_radio_answer4:
                                    answer = holder.answer4_text.getText().toString();
                                    break;
                                case R.id.test_rv_item_radio_answer5:
                                    answer = holder.answer5_text.getText().toString();
                                    break;
                            }
                            if(current_task.getCorrect_answer().equals(answer)){
                                presenter.tellViewToShowMessageThatUserFoundCorrectAnswer();
                                current_task.setStatus(1);
                                holder.status.setVisibility(View.VISIBLE);
                                //TODO:Синхронизация с бд
                            }else{
                                presenter.tellViewToShowMessageThatUserFoundIncorrectAnswer();
                            }
                        }

                    }
                });
            }else holder.answers_block.setVisibility(View.GONE);

            holder.show_answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.tellViewToShowDialogWithDescAnswer(current_task.getAnswer());
                }
            });

            holder.set_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current_task.setStatus(1);
                    holder.status.setVisibility(View.VISIBLE);
                    holder.set_done.setVisibility(View.GONE);
                    //TODO:Синхронизация с бд
                }
            });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView task,answer1_text,answer2_text,answer3_text,answer4_text,answer5_text,status;
        ImageView set_done, show_answer;
        Button check_answer;
        RadioGroup answers_block;
        RadioButton answer1_radio,answer2_radio,answer3_radio,answer4_radio,answer5_radio ,current_checked_button;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.test_rv_item_task);
            answer1_text = itemView.findViewById(R.id.test_rv_item_text_answer1);
            answer2_text = itemView.findViewById(R.id.test_rv_item_text_answer2);
            answer3_text = itemView.findViewById(R.id.test_rv_item_text_answer3);
            answer4_text = itemView.findViewById(R.id.test_rv_item_text_answer4);
            answer5_text = itemView.findViewById(R.id.test_rv_item_text_answer5);
            status = itemView.findViewById(R.id.test_rv_item_status);

            answer1_radio = itemView.findViewById(R.id.test_rv_item_radio_answer1);
            answer2_radio = itemView.findViewById(R.id.test_rv_item_radio_answer2);
            answer3_radio = itemView.findViewById(R.id.test_rv_item_radio_answer3);
            answer4_radio = itemView.findViewById(R.id.test_rv_item_radio_answer4);
            answer5_radio = itemView.findViewById(R.id.test_rv_item_radio_answer5);

            set_done = itemView.findViewById(R.id.test_rv_item_setDone);
            show_answer = itemView.findViewById(R.id.test_rv_item_showAnswer);

            check_answer = itemView.findViewById(R.id.test_rv_item_button_check);

            answers_block = itemView.findViewById(R.id.test_rv_item_radiogroup);


            RadioButton.OnClickListener ocl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(current_checked_button != null) current_checked_button.setChecked(false);
                    current_checked_button = (RadioButton) v;
                    current_checked_button.setChecked(true);
                }
            };
            answer1_radio.setOnClickListener(ocl);
            answer2_radio.setOnClickListener(ocl);
            answer3_radio.setOnClickListener(ocl);
            answer4_radio.setOnClickListener(ocl);
            answer5_radio.setOnClickListener(ocl);

        }
    }
}
