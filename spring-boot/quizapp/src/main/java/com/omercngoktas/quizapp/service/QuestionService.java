package com.omercngoktas.quizapp.service;

import com.omercngoktas.quizapp.dao.QuestionDao;
import com.omercngoktas.quizapp.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    private QuestionService(final QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
    }
}
