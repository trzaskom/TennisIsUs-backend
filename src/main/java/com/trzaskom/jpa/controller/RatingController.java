package com.trzaskom.jpa.controller;

import com.trzaskom.jpa.model.Rating;
import com.trzaskom.jpa.repository.RatingRepository;
import com.trzaskom.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/ratings")
    public List<Rating> getAllRatingsByUserId(@PathVariable (value = "userId") Long userId) {
        return ratingRepository.findByUserId(userId);
    }

/*    @PostMapping("/users/{userId}/ratings")
    public Rating addRating(@PathVariable (value = "userId") Long userId, @Valid @RequestBody Rating rating) {
        return userRepository.findById(userId).map(user -> {
            rating.setUser(user);
            return ratingRepository.save(rating);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }*/

}
