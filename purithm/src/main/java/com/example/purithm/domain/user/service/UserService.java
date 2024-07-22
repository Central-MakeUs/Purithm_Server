package com.example.purithm.domain.user.service;

import com.example.purithm.global.auth.dto.response.SocialUserInfoDto;
import com.example.purithm.domain.user.entity.User;
import com.example.purithm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public String signUp(SocialUserInfoDto socialUserInfoDto) {
    User existUser = userRepository.findByUsername(socialUserInfoDto.getUsername());

    if (existUser == null) {
      User user = User.builder()
          .profile(socialUserInfoDto.getProfile())
          .nickname(socialUserInfoDto.getNickname())
          .username(socialUserInfoDto.getUsername())
          .build();

      userRepository.save(user);
    }

    return socialUserInfoDto.getUsername();
  }
}
