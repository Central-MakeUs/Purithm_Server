package com.example.purithm.global.response;

import com.example.purithm.global.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
  private final int code;
  private final String message;

  public static ErrorResponse of(CustomException exception) {
    return new ErrorResponse(exception.getCode(), exception.getMessage());
  }
}
