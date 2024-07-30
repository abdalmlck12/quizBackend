package com.example.quiz.Service;

import com.example.quiz.Models.Result;
import com.example.quiz.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {


    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public List<Result> getResultByUserId(Integer userId) {
     return resultRepository.findByUserId(userId);
    }

    public List<Result> getResultsByQuiz(Integer quizId) {
        return resultRepository.findByQuizId(quizId);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }
}
