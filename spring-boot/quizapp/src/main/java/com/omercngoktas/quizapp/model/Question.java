package com.omercngoktas.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private String difficultyLevel;
    private String category;

    public Question(Long id, String questionTitle, String option1, String option2, String option3, String option4, String correctAnswer, String difficultyLevel, String category) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
        this.category = category;
    }

    public Question() {
    }

    public Long id() {
        return id;
    }

    public String questionTitle() {
        return questionTitle;
    }

    public String option1() {
        return option1;
    }

    public String option2() {
        return option2;
    }

    public String option3() {
        return option3;
    }

    public String option4() {
        return option4;
    }

    public String correctAnswer() {
        return correctAnswer;
    }

    public String difficultyLevel() {
        return difficultyLevel;
    }

    public String category() {
        return category;
    }

    public Question setId(Long id) {
        this.id = id;
        return this;
    }

    public Question setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
        return this;
    }

    public Question setOption1(String option1) {
        this.option1 = option1;
        return this;
    }

    public Question setOption2(String option2) {
        this.option2 = option2;
        return this;
    }

    public Question setOption3(String option3) {
        this.option3 = option3;
        return this;
    }

    public Question setOption4(String option4) {
        this.option4 = option4;
        return this;
    }

    public Question setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public Question setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }

    public Question setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
