package com.trzaskom.dto;

import com.trzaskom.jpa.model.User;

/**
 * Created by miki on 2019-03-21.
 */
public class UserSearchDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String gender;
    private Integer age;
    private Double rating;
    private double distanceFromCurrentUser;

    public UserSearchDTO(User user, double distanceFromCurrentUser){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.rating = user.getRating();
        this.distanceFromCurrentUser = distanceFromCurrentUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public double getDistanceFromCurrentUser() {
        return distanceFromCurrentUser;
    }

    public void setDistanceFromCurrentUser(double distanceFromCurrentUser) {
        this.distanceFromCurrentUser = distanceFromCurrentUser;
    }
}
