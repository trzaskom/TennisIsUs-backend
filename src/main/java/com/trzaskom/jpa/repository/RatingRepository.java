package com.trzaskom.jpa.repository;

import com.trzaskom.jpa.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long>{

    List<Rating> findByUserId(Long userId);

/*    @Query(value = "SELECT AVG(r.answer) FROM Ratings r WHERE r.user_id = ?1)", nativeQuery = true)
    double getOverallRating(Long userId);*/
}
