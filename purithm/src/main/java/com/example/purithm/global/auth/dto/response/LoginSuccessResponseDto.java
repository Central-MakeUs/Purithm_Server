package com.example.purithm.global.auth.dto.response;

import lombok.Builder;

@Builder
public class LoginSuccessResponseDto {
  int code;
  String message;
  String token;
}
