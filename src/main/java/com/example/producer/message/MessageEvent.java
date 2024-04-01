package com.example.producer.message;


import com.example.producer.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;

@Component
public class MessageEvent {

    @Value("${api.queue.message.exchange}")
    private String exchange;
    @Value("${api.queue.message.route}")
    private String route;

    private ObjectMapper objectMapper;
    private RabbitTemplate rabbitTemplate;

    public MessageEvent() {}

    @Autowired
    public MessageEvent(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    public void sendMessage(String message) throws JsonProcessingException {

        Message queueMessage = createMessage(message);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(route);
        rabbitTemplate.convertAndSend(message);
    }

    private Message createMessage (String message) throws JsonProcessingException {
        MessageProperties props = MessagePropertiesBuilder.newInstance().setContentType(CONTENT_TYPE_JSON).build();
        return MessageBuilder.withBody(objectMapper.writeValueAsBytes(MessageDTO.builder().message(message).build())).andProperties(props).build();

    }
}
