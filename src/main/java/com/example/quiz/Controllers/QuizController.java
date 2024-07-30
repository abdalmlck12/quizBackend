package com.example.quiz.Controllers;

import com.example.quiz.Models.Quiz;
import com.example.quiz.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/quizzes")
public class QuizController {


    private final QuizService quizService;
    @Autowired

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        try {
            Quiz createdQuiz = quizService.createQuiz(quiz);
            return ResponseEntity.ok(createdQuiz);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/category-name/{categoryName}")
    public ResponseEntity<List<Quiz>> getQuizzesByCategory(@PathVariable String categoryName) {
        List<Quiz> quizzes = quizService.getQuizByCategoryName(categoryName);
        return ResponseEntity.ok(quizzes);
    }
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuiz();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        return quiz.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
