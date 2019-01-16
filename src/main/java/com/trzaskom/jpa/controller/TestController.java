package com.trzaskom.jpa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by miki on 2019-01-05.
 */

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class TestController {

    @GetMapping("/public")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public String publicService() {
        return "This message is public";
    }

    @GetMapping("/secret")
    @CrossOrigin(origins = {"http://localhost:4200"})
    public String secretService() {
        return "A secret message";
    }
}
