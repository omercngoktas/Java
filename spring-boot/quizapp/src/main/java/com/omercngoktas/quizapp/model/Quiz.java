package com.omercngoktas.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(Long id, String title, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String title() {
        return title;
    }

    public Quiz setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long id() {
        return id;
    }

    public Quiz setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Question> questions() {
        return questions;
    }

    public Quiz setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
