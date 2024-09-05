package com.example.purithm.domain.user.service;

import java.time.LocalDateTime;

import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.UserInfoDto;
import com.example.purithm.domain.user.entity.Provider;
import com.example.purithm.global.auth.dto.request.LoginRequestDto;
import com.example.purithm.global.auth.dto.response.SignUpUserInfoDto;
import com.example.purithm.domain.user.entity.User;
import com.example.purithm.domain.user.repository.UserRepository;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Long signUp(SignUpUserInfoDto signUpUserInfoDto) {
    User existUser = userRepository
        .findByProviderAndProviderId(signUpUserInfoDto.getProvider(), signUpUserInfoDto.getProviderId());

    if (existUser != null) {
      if (existUser.getProvider().equals(Provider.PURITHM)) {
        throw CustomException.of(Error.NICKNAME_ALREADY_USED_ERROR);
      }
      return existUser.getId();
    }

    boolean isWithdrawnUser = userRepository.existsWithdrawnUser(
        signUpUserInfoDto.getProvider(),
        signUpUserInfoDto.getProviderId(),
        LocalDateTime.now().minusDays(7));
    if (isWithdrawnUser) {
      throw CustomException.of(Error.WIRHDRAWN_USER_ERROR);
    }

    User user = User.builder()
        .profile(signUpUserInfoDto.getProfile())
        .provider(signUpUserInfoDto.getProvider())
        .providerId(signUpUserInfoDto.getProviderId())
        .username(signUpUserInfoDto.getUsername())
        .email(signUpUserInfoDto.getEmail())
        .terms(false)
        .membership(Membership.BASIC)
        .password(passwordEncoder.encode(signUpUserInfoDto.getPassword()))
        .deletedAt(null)
        .build();

    Long savedUserId = null;
    try {
      User savedUser = userRepository.save(user);
      savedUserId = savedUser.getId();
    } catch (DuplicateKeyException e) {
      throw new RuntimeException("try to save duplicated user");
    }
    return savedUserId;
  }

  public void agreeToTermsOfUse(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
    user.agreeToTerms();
    userRepository.save(user);
  }

  public boolean checkTerms(Long id) {
    return userRepository.findById(id).orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR))
        .isTerms();
  }

  public AccountInfoDto getUserAccountInfo(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

    return AccountInfoDto.builder()
        .provider(user.getProvider())
        .createdAt(user.getCreatedAt())
        .email(user.getEmail())
        .build();
  }

  public UserInfoDto getUserInfo(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

    int likes = userRepository.countLikesByUserId(userId);
    int filterViewCount = userRepository.countLogsByUserId(userId);
    int reviews = userRepository.countReviewsByUserId(userId);

    return UserInfoDto.builder()
        .id(user.getId())
        .name(user.getUsername())
        .profile(user.getProfile())
        .stamp(reviews)
        .likes(likes)
        .filterViewCount(filterViewCount)
        .reviews(reviews)
        .build();
  }

  public void updateProfile(UserInfoRequestDto userInfo, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

    user.updateProfile(userInfo);
    userRepository.save(user);
  }

  public Long getUserId(LoginRequestDto loginRequestDto) {
    User user = userRepository.findByProviderAndProviderId(Provider.PURITHM, loginRequestDto.id());
    if (user == null) {
      throw CustomException.of(Error.NOT_FOUND_ERROR);
    }

    if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
      throw CustomException.of(Error.INVALID_ID_PASSWORD);
    }
    return user.getId();
  }

  public void deleteUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
    user.withdraw();
    userRepository.save(user);
  }
}
