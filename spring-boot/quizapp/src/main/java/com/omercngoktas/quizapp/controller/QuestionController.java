package com.omercngoktas.quizapp.controller;

import com.omercngoktas.quizapp.model.Question;
import com.omercngoktas.quizapp.service.QuestionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(value="/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    private QuestionController(final QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
}
