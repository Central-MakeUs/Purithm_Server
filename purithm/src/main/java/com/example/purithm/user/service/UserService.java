package com.example.purithm.user.service;

import com.example.purithm.auth.dto.response.KakaoUserInfoResponseDto;
import com.example.purithm.user.entity.User;
import com.example.purithm.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public String signUpKakaoUser(KakaoUserInfoResponseDto kakaoUserInfoResponseDto) {
    String username = "KAKAO "+kakaoUserInfoResponseDto.getId();
    User existUser = userRepository.findByUsername(username);

    if (existUser == null) {
      User user = User.builder()
          .profile(kakaoUserInfoResponseDto.getProperties().getProfile_image())
          .nickname(kakaoUserInfoResponseDto.getProperties().getNickname())
          .username(username)
          .build();

      userRepository.save(user);
    }

    return username;
  }
}
