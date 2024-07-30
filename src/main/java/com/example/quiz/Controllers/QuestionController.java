package com.example.quiz.Controllers;


import com.example.quiz.Models.Question;
import com.example.quiz.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService ;

    public  QuestionController (QuestionService questionService){
        this.questionService=questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(
            @RequestBody Question question
    ) {
      Question newQuestion =questionService.createQuestion(question);
      return  ResponseEntity.ok(newQuestion);
    }
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Integer quizId) {
        List<Question> questions = questionService.getQuestionsByQuiz(quizId);
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
