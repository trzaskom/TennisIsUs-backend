package com.trzaskom.utils;


import com.trzaskom.jpa.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by miki on 2019-03-20.
 */
public class AuthorizationUtils {


    private static UserRepository userRepository;

    public static String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
