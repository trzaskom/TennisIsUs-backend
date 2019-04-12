package com.trzaskom.utils;


import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by miki on 2019-03-20.
 */
public class AuthorizationUtils {

    public static String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
