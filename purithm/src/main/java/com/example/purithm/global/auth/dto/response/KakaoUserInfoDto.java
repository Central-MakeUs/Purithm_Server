package com.example.purithm.global.auth.dto.response;

import lombok.Getter;

@Getter
public class KakaoUserInfoDto {
  private Long id;
  private String connected_at;
  private KakaoProperties properties;

  @Getter
  public class KakaoProperties {
    private String nickname;
    private String profile_image;
  }
}
