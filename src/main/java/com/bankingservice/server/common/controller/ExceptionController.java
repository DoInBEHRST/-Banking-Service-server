package com.bankingservice.server.common.controller;

import com.bankingservice.server.common.dto.ResponseErrorMessage;
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
                ResponseErrorMessage.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message(exception.getMessage())
                    .build()
            );
    }

    // 500 에러
    @ExceptionHandler({
        IllegalArgumentException.class
    })
    public ResponseEntity<Object> handleBusinessException(final Exception exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ResponseErrorMessage.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(exception.getMessage())
                    .build()
            );
    }

}
