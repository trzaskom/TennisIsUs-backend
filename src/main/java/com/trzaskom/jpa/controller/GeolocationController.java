package com.trzaskom.jpa.controller;

import com.trzaskom.jpa.model.Geolocation;
import com.trzaskom.jpa.model.User;
import com.trzaskom.jpa.repository.GeolocationRepository;
import com.trzaskom.jpa.repository.UserRepository;
import com.trzaskom.utils.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by miki on 2019-01-19.
 */

@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"})
public class GeolocationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GeolocationRepository geolocationRepository;

    @PostMapping("/geolocation")
    public Geolocation postGeolocation(@Valid @RequestBody Geolocation geolocation){
        User user = userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());
        geolocation.setUser(user);
        return geolocationRepository.save(geolocation);
    }

    @PutMapping("/geolocation")
    public Geolocation putGeolocation(@Valid @RequestBody Geolocation geolocation){
        Long user = userRepository.findByUsername(AuthorizationUtils.getCurrentUsername()).getId();
        geolocation.setId(user);
        return geolocationRepository.save(geolocation);
    }
}
