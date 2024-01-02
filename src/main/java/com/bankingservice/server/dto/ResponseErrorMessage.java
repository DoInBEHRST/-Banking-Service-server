package com.bankingservice.server.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseErrorMessage {

    private int code;
    private String message;
}