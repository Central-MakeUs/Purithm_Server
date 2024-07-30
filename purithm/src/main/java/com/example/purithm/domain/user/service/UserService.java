package com.example.purithm.domain.user.service;

import com.example.purithm.global.auth.dto.response.SocialUserInfoDto;
import com.example.purithm.domain.user.entity.User;
import com.example.purithm.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public Long signUp(SocialUserInfoDto socialUserInfoDto) {
    User existUser = userRepository.findByProviderId(socialUserInfoDto.getProviderId());

    if (existUser != null) {
      return existUser.getId();
    }

    User user = User.builder()
        .profile(socialUserInfoDto.getProfile())
        .providerId(socialUserInfoDto.getProviderId())
        .username(socialUserInfoDto.getUsername())
        .build();

    User savedUser = userRepository.save(user);
    return savedUser.getId();
  }
}
