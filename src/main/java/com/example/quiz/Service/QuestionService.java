package com.example.quiz.Service;

import com.example.quiz.Models.Question;
import com.example.quiz.Repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }
    public Optional<Question> getQuestionById(Integer id){
        return questionRepository.findById(id);
    }
    public List<Question> getQuestionsByQuiz(Integer quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
