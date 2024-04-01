package com.example.producer.event;

import com.example.producer.event.model.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishEvent(String message){
        CustomEvent event = new CustomEvent(this, message);
        publisher.publishEvent(event);
    }
}
