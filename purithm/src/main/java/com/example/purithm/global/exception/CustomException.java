package com.example.purithm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
  private final HttpStatus httpStatus;
  private final int code;
  private final String message;

  public static CustomException of(Error error) {
    return new CustomException(error.getHttpStatus(), error.getCode(), error.getMessage());
  }
}
