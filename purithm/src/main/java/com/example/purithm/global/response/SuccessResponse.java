package com.example.purithm.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
  private final int code;
  private final String message;
  private T data;

  public static SuccessResponse of() {
    return new SuccessResponse<>(HttpStatus.OK.value(), "ok");
  }

  public static <T> SuccessResponse<T> of(T data) {
    return new SuccessResponse<>(HttpStatus.OK.value(), "ok", data);
  }
}