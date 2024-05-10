package com.team1.technikon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseApi <T> {
    private int code;
    private String message;
    private T data;
    // nothing
}
