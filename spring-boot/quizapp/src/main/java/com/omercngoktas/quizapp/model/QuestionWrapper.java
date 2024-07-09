package com.omercngoktas.quizapp.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private Long id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Long id, String option1, String questionTitle, String option2, String option3, String option4) {

        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public Long id() {
        return id;
    }

    public QuestionWrapper setId(Long id) {
        this.id = id;
        return this;
    }

    public String questionTitle() {
        return questionTitle;
    }

    public QuestionWrapper setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
        return this;
    }

    public String option1() {
        return option1;
    }

    public QuestionWrapper setOption1(String option1) {
        this.option1 = option1;
        return this;
    }

    public String option2() {
        return option2;
    }

    public QuestionWrapper setOption2(String option2) {
        this.option2 = option2;
        return this;
    }

    public String option3() {
        return option3;
    }

    public QuestionWrapper setOption3(String option3) {
        this.option3 = option3;
        return this;
    }

    public String option4() {
        return option4;
    }

    public QuestionWrapper setOption4(String option4) {
        this.option4 = option4;
        return this;
    }
}
