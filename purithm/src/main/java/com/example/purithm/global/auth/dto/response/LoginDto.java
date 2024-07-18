package com.example.purithm.global.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginDto {
  int code;
  String message;
  String token;
}
