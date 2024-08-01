package com.example.quiz.Controllers;


import com.example.quiz.Models.Result;
import com.example.quiz.Models.User;
import com.example.quiz.Repository.UserRepository;
import com.example.quiz.Service.ResultService;
import com.example.quiz.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final ResultService resultService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private  final UserService userService;

@Autowired
public  UserController(UserService userService, ResultService resultService ){
    this.userService=userService;
    this.resultService=resultService;
}


    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        logger.info("Received signup request for user: {}", user);

        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            logger.warn("User already exists: {}", existingUser.get());
            return ResponseEntity.status(409).body("User already exists");
        }

        try {
            User registeredUser = userService.registerUser(user);
            logger.info("User registered successfully: {}", registeredUser);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            logger.error("Error registering user: ", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userService.validateUser(user);
        // Return the user ID
        return existingUser.<ResponseEntity<?>>map(value ->
                        ResponseEntity.ok( value.getId()))
                .orElseGet(() -> ResponseEntity.status(401).body("User not found"));
    }
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
    try {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    } catch (Exception e) {
        logger.error("Error deleting user with id {}: {}", id, e.getMessage());
        return ResponseEntity.status(500).body("Error deleting user");
    }
}



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserID(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}/results")
    public ResponseEntity<List<Result>> getResultsByUser(@PathVariable Integer id) {
        List<Result> results = resultService.getResultByUserId(id);
        return ResponseEntity.ok(results);
    }


}
