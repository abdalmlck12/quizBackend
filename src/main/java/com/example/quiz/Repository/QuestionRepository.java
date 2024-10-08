package com.example.quiz.Repository;

import com.example.quiz.Models.Question;
import com.example.quiz.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List <Question> findByQuizId (int quizId);  ;
}
