package com.example.purithm.global.auth.controller;

import com.example.purithm.global.auth.dto.response.KakaoUserInfoDto;
import com.example.purithm.global.auth.dto.response.SocialUserInfoDto;
import com.example.purithm.global.auth.jwt.JWTUtil;
import com.example.purithm.global.config.WebClientConfig;
import com.example.purithm.domain.user.service.UserService;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;
import com.example.purithm.global.response.SuccessResponse;
import com.nimbusds.jose.JOSEException;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.text.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

  private final WebClientConfig webClientConfig;
  private final UserService userService;
  private final JWTUtil jwtUtil;

  @GetMapping("/kakao")
  public Mono<SuccessResponse<String>> kakaoLogin(String token) {
    return webClientConfig.webClient()
        .post()
        .uri("https://kapi.kakao.com/v2/user/me")
        .header("Authorization", token)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue("property_keys=[\"properties.nickname\", \"properties.profile_image\"]")
        .retrieve()
        .bodyToMono(KakaoUserInfoDto.class)
        .flatMap(res -> {

          SocialUserInfoDto userInfoDto = SocialUserInfoDto.builder()
              .profile(res.getProperties().getProfile_image())
              .nickname(res.getProperties().getNickname())
              .username("KAKAO " + res.getId())
              .build();
          String username = userService.signUp(userInfoDto);
          String jwtToken = jwtUtil.createJwt(username, 60 * 60 * 60 * 1000L);

          SuccessResponse<String> body = SuccessResponse.of(jwtToken);
          return Mono.just(body);
        })
        .onErrorResume(err -> {
          throw CustomException.of(Error.INVALID_TOKEN_ERROR);
        });
  }

  @GetMapping("/apple")
  public SuccessResponse<String> appleLogin(String token, String email)
      throws IOException, ParseException, JOSEException {

    token = token.substring(7);
    Claims claims = jwtUtil.getClaims(token);

    SocialUserInfoDto userInfoDto = SocialUserInfoDto.builder()
        .nickname((String) claims.get("nickname"))
        .username("APPLE " + (String) claims.get("sub"))
        .profile(null)
        .build();

    String username = userService.signUp(userInfoDto);
    String jwtToken = jwtUtil.createJwt(username, 60 * 60 * 60 * 1000L);

    return SuccessResponse.of(jwtToken);
  }
}
