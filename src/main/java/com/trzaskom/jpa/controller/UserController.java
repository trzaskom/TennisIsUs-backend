package com.trzaskom.jpa.controller;

import com.trzaskom.jpa.model.User;
import com.trzaskom.jpa.repository.UserRepository;
import com.trzaskom.jpa.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId).map(user -> {
            user.setName(userRequest.getName());
            user.setSurname(userRequest.getSurname());
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setAge(userRequest.getAge());
            user.setGender(userRequest.getGender());
            user.setRating(userRequest.getRating());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/rating")
    public User updateRating(@Valid @RequestBody Double rating) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userRepository.findByUsername(username).getId();
        return userRepository.findById(userId).map(user -> {
                user.setRating(rating);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return  userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId" + userId + "not found"));
    }
}
