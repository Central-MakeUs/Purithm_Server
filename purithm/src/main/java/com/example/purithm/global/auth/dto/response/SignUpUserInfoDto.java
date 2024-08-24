package com.example.purithm.global.auth.dto.response;

import com.example.purithm.domain.user.entity.Provider;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpUserInfoDto {
  private String username;
  private Provider provider;
  private String providerId;
  private String profile;
  private String email;
  private String password;
}
