package com.trzaskom.socket.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/socket")
@CrossOrigin("*")
public class SocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send/message")
    public Map<String, String> broadcastNotification(String message) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> messageConverted;
        try {
            messageConverted = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            messageConverted = null;
        }
        if (messageConverted != null) {
            if (messageConverted.containsKey("toId") && messageConverted.get("toId") != null && !messageConverted.get("toId").equals("")) {
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("toId"), messageConverted);
                this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + messageConverted.get("fromId"), message);
            }
        }
        return messageConverted;
    }
}