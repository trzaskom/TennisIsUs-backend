package com.trzaskom.jpa.repository;

import com.trzaskom.jpa.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by miki on 2019-01-19.
 */

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {
}


