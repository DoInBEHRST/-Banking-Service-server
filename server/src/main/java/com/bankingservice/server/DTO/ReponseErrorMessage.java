package com.bankingservice.server.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReponseErrorMessage {

    private int code;
    private String message;
}