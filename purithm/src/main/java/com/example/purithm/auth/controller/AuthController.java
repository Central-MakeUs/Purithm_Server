package com.example.purithm.auth.controller;

import com.example.purithm.auth.dto.response.KakaoUserInfoResponseDto;
import com.example.purithm.auth.dto.response.LoginResponseDto;
import com.example.purithm.auth.jwt.JWTUtil;
import com.example.purithm.config.WebClientConfig;
import com.example.purithm.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final WebClientConfig webClientConfig;
  private final UserService userService;
  private final JWTUtil jwtUtil;

  @GetMapping("/kakao")
  public Mono<LoginResponseDto> kakaoLogin(@RequestHeader("Authorization") String token) {
    return webClientConfig.webClient()
        .post()
        .uri("https://kapi.kakao.com/v2/user/me")
        .header("Authorization", token)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue("property_keys=[\"properties.nickname\", \"properties.profile_image\"]")
        .retrieve()
        .bodyToMono(KakaoUserInfoResponseDto.class)
        .flatMap(res -> {
          String username = userService.signUpKakaoUser(res);
          String jwtToken = jwtUtil.createJwt(username, 60 * 60 * 60 * 1000L);

          LoginResponseDto body = LoginResponseDto.builder()
              .code(200).message("login success").token(jwtToken).build();
          return Mono.just(body);
        })
        .onErrorResume(err -> {
          LoginResponseDto body = LoginResponseDto.builder()
              .code(401).message(err.getMessage()).token(null).build();
          return Mono.just(body);
        });
  }
}
