package com.example.quiz.Repository;

import com.example.quiz.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz , Integer > {
    List<Quiz> findByCategoryId  (Integer categoryId); ;

    List<Quiz> findByUserId  (Integer userId);  ;



    @Query("SELECT q FROM Quiz q JOIN q.category c WHERE c.categoryName = :categoryName")
    List<Quiz> findByCategoryName(@Param("categoryName") String categoryName);
}
