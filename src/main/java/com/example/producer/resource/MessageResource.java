package com.example.producer.resource;

import com.example.producer.service.EventService;
import com.example.producer.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageResource {

    private final MessageService messageService;
    private final EventService eventService;

    @PutMapping(value = "/message")
    public ResponseEntity<?> publishMessage(@RequestParam String message) throws JsonProcessingException {
        messageService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/event")
    public ResponseEntity<?> publishEvent(@RequestParam String message) throws JsonProcessingException {
        eventService.publishEvent(message);
        return ResponseEntity.ok().build();
    }
}
