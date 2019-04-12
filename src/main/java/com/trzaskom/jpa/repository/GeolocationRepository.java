package com.trzaskom.jpa.repository;

import com.trzaskom.jpa.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by miki on 2019-01-19.
 */

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {

    @Query(value = "SELECT geolocations.* FROM geolocations JOIN users ON (users.id = geolocations.user_id AND (users.rating BETWEEN ?1 AND ?2) AND (users.age BETWEEN ?3 AND ?4) AND users.id != ?5)", nativeQuery = true)
    List<Geolocation> findByCryteriaBarringGender(Double lowestRating, Double highestRating, Integer youngest, Integer oldest, Long userId);

    @Query(value = "SELECT geolocations.* FROM geolocations JOIN users ON (users.id = geolocations.user_id AND (users.rating BETWEEN ?1 AND ?2) AND (users.age BETWEEN ?3 AND ?4) AND users.id != ?5 AND users.gender = ?6)", nativeQuery = true)
    List<Geolocation> findByCryteriaWithGender(Double lowestRating, Double highestRating, Integer youngest, Integer oldest, Long userId, Integer genderCode);

}


