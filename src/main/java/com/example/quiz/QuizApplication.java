package com.example.quiz;

import com.example.quiz.Models.Result;
import com.example.quiz.Models.User;
import com.example.quiz.Repository.ResultRepository;
import com.example.quiz.Repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuizApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner inti (
//			UserRepository userRepository
//			){
//		return   args -> {
//			for (int i = 0; i < 50; i++) {
//				Faker faker = new Faker();
//
//				if (userRepository != null) {
//					var user = User.builder()
//							.name(faker.name().name())
//							.email(faker.internet().emailAddress() )
//							.password(faker.internet().password() )
//							.build();
//					userRepository.save(user);
//				} else {
//					System.out.println("AuthorRepository is null");
//				}
//
//
//			}
//
//
//		};
//	}

}
