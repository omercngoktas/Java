package com.omercngoktas.quizapp.dao;

import com.omercngoktas.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Long> {

}
