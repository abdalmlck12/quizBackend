package com.example.quiz.Controllers;

import com.example.quiz.Models.Result;
import com.example.quiz.Service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }


    @PostMapping
    public Result submitResult(@RequestBody Result result) {
        return resultService.saveResult(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getResultsByUser(@PathVariable Integer userId) {
        List<Result> results = resultService.getResultByUserId(userId);
        if (results.isEmpty()) {
            // Return a custom message when no results are found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found for user with ID " + userId);
        }
        return ResponseEntity.ok(results);
    }
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Result>> getResultsByQuiz(@PathVariable Integer quizId) {
        List<Result> results = resultService.getResultsByQuiz(quizId);
        return ResponseEntity.ok(results);
    }





}
