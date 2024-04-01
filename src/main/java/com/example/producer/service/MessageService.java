package com.example.producer.service;

import com.example.producer.message.MessageEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageEvent messageEvent;

    public void sendMessage(String message) throws JsonProcessingException {
        messageEvent.sendMessage(message);
    }
}
