package com.example.producer.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 7413601253607597026L;

    private String message;
}
