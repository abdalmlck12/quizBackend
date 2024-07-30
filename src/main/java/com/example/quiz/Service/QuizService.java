package com.example.quiz.Service;

import com.example.quiz.Models.Category;
import com.example.quiz.Models.Quiz;
import com.example.quiz.Models.User;
import com.example.quiz.Repository.CategoryRepository;
import com.example.quiz.Repository.QuizRepository;
import com.example.quiz.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Quiz createQuiz(Quiz quiz) {
        logger.info("Received quiz: {}", quiz);
        Optional<Category> categoryOpt = categoryRepository.findById(quiz.getCategory().getId());
        Optional<User> userOpt = userRepository.findById(quiz.getUser().getId());

        if (categoryOpt.isPresent() && userOpt.isPresent()) {
            quiz.setCategory(categoryOpt.get());
            quiz.setUser(userOpt.get());

            // Ensure that each question is associated with the quiz
            quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
            logger.info("Saving quiz: {}", quiz);
            return quizRepository.save(quiz);
        } else {
            logger.error("Invalid category or user ID");
            throw new IllegalArgumentException("Invalid category or user ID");
        }
    }



    public List<Quiz> getQuizByCategoryName(String category) {
      return quizRepository.findByCategoryName(category);
    }

    public  List<Quiz> getAllQuiz() {
    return quizRepository.findAll();
}
    public List<Quiz> getQuizzesByUser(Integer   userId) {
        return quizRepository.findByUserId(userId);
    }

    public Optional<Quiz> getQuizById(Integer id){
        return quizRepository.findById(id);
    }
    public void deleteQuiz(Integer id) {
        quizRepository.deleteById(id);
    }
}
