package com.trzaskom.jpa.controller;

import com.trzaskom.jpa.model.Friends;
import com.trzaskom.jpa.model.User;
import com.trzaskom.jpa.repository.FriendsRepository;
import com.trzaskom.jpa.repository.UserRepository;
import com.trzaskom.utils.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class FriendsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendsRepository friendsRepository;

    @PostMapping("/friends")
    public Friends postFriendship(@RequestBody Long friendId){
        User user = this.userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());

        if (this.friendsRepository.existsFriendsByIds(user.getId(), friendId) == 1)
            return null;

        User friend = this.userRepository.findById(friendId).get();
        Friends friends = new Friends(user, friend);
        return this.friendsRepository.save(friends);
    }

    @GetMapping("/friends")
    public List<User> getUsersFriends(){
        User user = this.userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());
        List<Friends> friendships = this.friendsRepository.findAllUserFriends(user.getId());
        List<User> friends = new ArrayList<>();
        for (Friends friend : friendships){
            if (friend.getUser1().equals(user))
                friends.add(friend.getUser2());
            else
                friends.add(friend.getUser1());
        }
        return friends;
    }
}
