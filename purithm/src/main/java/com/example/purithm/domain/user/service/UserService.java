package com.example.purithm.domain.user.service;

import com.example.purithm.global.auth.dto.response.SocialUserInfoDto;
import com.example.purithm.domain.user.entity.User;
import com.example.purithm.domain.user.repository.UserRepository;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;

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
        .terms(false)
        .build();

    User savedUser = userRepository.save(user);
    return savedUser.getId();
  }

  public void agreeToTermsOfUse(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
    user.agreeToTerms();
    userRepository.save(user);
  }

  public boolean checkTerms(Long id) {
    return userRepository.findById(id).orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR))
        .isTerms();
  }
}
