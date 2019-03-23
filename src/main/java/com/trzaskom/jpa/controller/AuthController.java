package com.trzaskom.jpa.controller;

import com.trzaskom.TennisIsUsApplication;
import com.trzaskom.jpa.model.User;
import com.trzaskom.jpa.repository.UserRepository;
import com.trzaskom.security.jwt.TokenProvider;
import com.trzaskom.utils.AuthorizationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by miki on 2019-01-04.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"})
public class AuthController {

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthController(PasswordEncoder passwordEncoder, UserRepository userRepository,
                          TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/authenticate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authenticate() {
        // we don't have to do anything here
        // this is just a secure endpoint and the JWTFilter
        // validates the token
        // this service is called at startup of the app to check
        // if the jwt token is still valid
    }

    @PostMapping("/login")
    public String authorize(@Valid @RequestBody User loginUser,
                            HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword());

        try {
            this.authenticationManager.authenticate(authenticationToken);
            return this.tokenProvider.createToken(loginUser.getUsername());
        }
        catch (AuthenticationException e) {
            TennisIsUsApplication.logger.info("Security exception {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User signupUser) {
        if (this.userRepository.existsByUsername(signupUser.getUsername())) {
            return "EXISTS";
        }

        signupUser.encodePassword(this.passwordEncoder);
        this.userRepository.save(signupUser);
        return this.tokenProvider.createToken(signupUser.getUsername());
    }

    @GetMapping("/currentUser")
    public User getCurrentUser(){
        User user = this.userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());
        return user;
        }

}
