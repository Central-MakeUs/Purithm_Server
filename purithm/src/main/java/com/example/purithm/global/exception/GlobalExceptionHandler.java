package com.example.purithm.global.exception;

import com.example.purithm.global.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
    ErrorResponse response = ErrorResponse.of(e);
    return new ResponseEntity<>(response, e.getHttpStatus());
  }
}
