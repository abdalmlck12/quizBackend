package com.example.quiz.Repository;

import com.example.quiz.Models.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ResultRepository extends JpaRepository<Result, Integer> {

    List<Result> findByUserId(Integer userId);

    List<Result> findByQuizId(Integer quizId);

}
