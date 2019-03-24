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
import java.util.List;
import java.util.Optional;

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
                                             @RequestParam("minRating") String minRating, @RequestParam("maxRating") String maxRating) {

        User currentUser = this.userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());
        Optional<Geolocation> usersGeolocation = this.geolocationRepository.findById(currentUser.getId());
        List<UserSearchDTO> nearbyUsers = new ArrayList<>();

        if (usersGeolocation.isPresent()) {

            User userInRange;
            double distance;
            List<Geolocation> othersGeolocations = this.geolocationRepository.findAll();
            othersGeolocations.remove(usersGeolocation.get());

            for (Geolocation geolocation : othersGeolocations) {

                distance = GeolocationUtils.distanceBetweenTwoUsers(usersGeolocation.get().getLatitude(),
                        usersGeolocation.get().getLongitude(), geolocation.getLatitude(), geolocation.getLongitude());

                if (distance <= Double.parseDouble(maxRange)) {
                    userInRange = geolocation.getUser();
                    if (Double.parseDouble(minRating) <= userInRange.getRating() &&
                            userInRange.getRating() <= Double.parseDouble(maxRating))
                        nearbyUsers.add(new UserSearchDTO(userInRange, distance,
                                geolocation.getLatitude(), geolocation.getLongitude()));
                }
            }

        }
        return nearbyUsers;
    }
}

