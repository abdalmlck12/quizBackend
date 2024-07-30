package com.example.quiz.Service;


import com.example.quiz.Models.User;
import com.example.quiz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public  UserService (UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> validateUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .filter(existingUser -> existingUser.getPassword().equals(user.getPassword()));
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    public Optional<User> getUserID(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }




}
