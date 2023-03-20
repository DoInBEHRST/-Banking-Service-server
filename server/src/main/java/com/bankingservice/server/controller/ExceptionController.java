package com.bankingservice.server.controller;

import com.bankingservice.server.DTO.ReponseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {



    // 401 에러
    @ExceptionHandler({
        IllegalStateException.class
    })
    public ResponseEntity<Object> handleAccessDeniedException(final Exception exception) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                ReponseErrorMessage.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message(exception.getMessage())
                    .build()
            );
    }

}
