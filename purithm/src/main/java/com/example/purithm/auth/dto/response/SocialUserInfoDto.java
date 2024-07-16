package com.example.purithm.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialUserInfoDto {
  private String nickname;
  private String username;
  private String profile;
}
