package com.trzaskom.jpa.controller;

import com.trzaskom.dto.MessageDTO;
import com.trzaskom.jpa.model.Message;
import com.trzaskom.jpa.model.User;
import com.trzaskom.jpa.repository.MessageRepository;
import com.trzaskom.jpa.repository.UserRepository;
import com.trzaskom.utils.AuthorizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public List<MessageDTO> getAllMessages(){
        User user = this.userRepository.findByUsername(AuthorizationUtils.getCurrentUsername());
        List<Message> messages = this.messageRepository.getAllByLoggedUser(user.getId());
        List<MessageDTO> messagesDTO = new ArrayList<>();
        for (Message message : messages){
            messagesDTO.add(new MessageDTO(message));
        }
        return messagesDTO;
    }


}
