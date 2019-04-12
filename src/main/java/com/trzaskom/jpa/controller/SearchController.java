package com.trzaskom.jpa.controller;

import com.trzaskom.dto.UserSearchDTO;
import com.trzaskom.jpa.model.Geolocation;
import com.trzaskom.jpa.model.User;
import com.trzaskom.jpa.repository.GeolocationRepository;
import com.trzaskom.jpa.repository.UserRepository;
import com.trzaskom.utils.AuthorizationUtils;
import com.trzaskom.utils.GeolocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by miki on 2019-03-19.
 */

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class SearchController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GeolocationRepository geolocationRepository;

    @GetMapping("/search")
    public List<UserSearchDTO> nearbyPlayers(@RequestParam("maxRange") String maxRange,
                                             @RequestParam("minRating") String minRating,
                                             @RequestParam("maxRating") String maxRating,
                                             @RequestParam("minAge") String minAge,
                                             @RequestParam("maxAge") String maxAge,
                                             @RequestParam("gender") String gender) {

        List<UserSearchDTO> nearbyUsers = new ArrayList<>();
        List<Geolocation> othersGeolocations;
        double distance;
        Integer searchRadius = Integer.parseInt(maxRange);
        Double lowestRating = Double.parseDouble(minRating);
        Double highestRating = Double.parseDouble(maxRating);
        Integer youngest = Integer.parseInt(minAge);
        Integer oldest = Integer.parseInt(maxAge);
        Integer genderCode = Integer.parseInt(gender);
        User currentUser = this.userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());
        Geolocation userGeolocation = this.geolocationRepository.findById(currentUser.getId()).get();

        if (genderCode == 3)
            othersGeolocations = this.geolocationRepository
                    .findByCryteriaBarringGender(lowestRating, highestRating, youngest, oldest, currentUser.getId());
        else
            othersGeolocations = this.geolocationRepository
                    .findByCryteriaWithGender(lowestRating, highestRating, youngest, oldest, currentUser.getId(), genderCode);

        for (Geolocation geolocation : othersGeolocations) {
            distance = GeolocationUtils.distanceBetweenTwoUsers(userGeolocation.getLatitude(),
                    userGeolocation.getLongitude(), geolocation.getLatitude(), geolocation.getLongitude());
            if (distance <= searchRadius)
                nearbyUsers.add(new UserSearchDTO(geolocation.getUser(), distance, geolocation.getLatitude(), geolocation.getLongitude()));
        }
        Collections.sort(nearbyUsers);
        return nearbyUsers;
    }
}

