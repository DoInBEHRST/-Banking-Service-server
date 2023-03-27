package com.bankingservice.server.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReponseErrorMessage {

    private int code;
    private String message;
}