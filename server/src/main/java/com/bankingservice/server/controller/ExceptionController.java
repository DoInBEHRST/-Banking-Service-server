package com.bankingservice.server.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @AllArgsConstructor
    @Getter
    static class ResponseErrorMessage {

        private String message;
    }

    // 401 에러
    @ExceptionHandler({
        IllegalStateException.class
    })
    public ResponseEntity<Object> handleAccessDeniedException(final Exception exception) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                new ResponseErrorMessage(exception.getMessage())
            );
    }

}
