package com.example.producer.service;

import com.example.producer.event.CustomEventPublisher;
import com.example.producer.event.model.CustomEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final CustomEventPublisher customEventPublisher;


    public void publishEvent(String message) {
        customEventPublisher.publishEvent(message);
    }
}
