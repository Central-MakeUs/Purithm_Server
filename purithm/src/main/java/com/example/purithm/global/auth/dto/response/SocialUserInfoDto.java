package com.example.purithm.global.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialUserInfoDto {
  private String username;
  private String providerId;
  private String profile;
}
