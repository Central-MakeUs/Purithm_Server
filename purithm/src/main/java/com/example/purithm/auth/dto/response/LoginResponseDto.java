package com.example.purithm.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDto {
  int code;
  String message;
  String token;
}
