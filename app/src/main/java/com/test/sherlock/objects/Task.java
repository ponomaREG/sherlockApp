package com.test.sherlock.objects;

public class Task {
    private String task , title, answer, answer1 , answer2, answer3, answer4, answer5, correct_answer;
    private int type_task;
    private int status;
    private int id;
    private int section;

    public Task setSection(int section) {
        this.section = section;
        return this;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Task setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public Task setAnswer1(String answer1) {
        this.answer1 = answer1;
        return this;
    }

    public Task setAnswer2(String answer2) {
        this.answer2 = answer2;
        return this;
    }

    public Task setAnswer3(String answer3) {
        this.answer3 = answer3;
        return this;
    }

    public Task setAnswer4(String answer4) {
        this.answer4 = answer4;
        return this;
    }

    public Task setAnswer5(String answer5) {
        this.answer5 = answer5;
        return this;
    }

    public Task setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
        return this;
    }

    public Task setTask(String task) {
        this.task = task;
        return this;
    }

    public Task setTitle(String title) {
        this.title = title;
        return this;
    }

    public Task setType_task(int type_task) {
        this.type_task = type_task;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_task() {
        return type_task;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getAnswer5() {
        return answer5;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getTask() {
        return task;
    }

    public String getTitle() {
        return title;
    }

    public int getSection() {
        return section;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}
