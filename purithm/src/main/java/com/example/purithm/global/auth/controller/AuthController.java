package com.example.purithm.global.auth.controller;

import com.example.purithm.domain.user.entity.Provider;
import com.example.purithm.global.auth.dto.request.LoginRequestDto;
import com.example.purithm.global.auth.dto.request.SignUpRequestDto;
import com.example.purithm.global.auth.dto.response.KakaoUserInfoDto;
import com.example.purithm.global.auth.dto.response.LoginDto;
import com.example.purithm.global.auth.dto.response.SignUpUserInfoDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

  private final WebClientConfig webClientConfig;
  private final UserService userService;
  private final JWTUtil jwtUtil;

  @PostMapping("/login")
  public SuccessResponse<LoginDto> login(LoginRequestDto loginRequestDto) {
      Long id = userService.getUserId(loginRequestDto);
      String jwtToken = jwtUtil.createJwt(id, 60 * 60 * 60 * 1000L);

      LoginDto loginDto = LoginDto.builder()
          .accessToken(jwtToken).build();
      return SuccessResponse.of(loginDto);
  }

  @PostMapping("/signup")
  public SuccessResponse signup(SignUpRequestDto signUpRequestDto) {
      SignUpUserInfoDto userInfoDto = SignUpUserInfoDto.builder()
          .profile(signUpRequestDto.profile())
          .username(signUpRequestDto.username())
          .provider(Provider.PURITHM)
          .providerId(signUpRequestDto.id())
          .email(signUpRequestDto.email())
          .password(signUpRequestDto.password())
          .build();

      Long id = userService.signUp(userInfoDto);

      return SuccessResponse.of();
  }

  @GetMapping("/kakao")
  public Mono<SuccessResponse<LoginDto>> kakaoLogin(String token) {
    return webClientConfig.webClient()
        .post()
        .uri("https://kapi.kakao.com/v2/user/me")
        .header("Authorization", token)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue("property_keys=[\"properties.nickname\", \"properties.profile_image\", \"kakao_account.email\"]")
        .retrieve()
        .bodyToMono(KakaoUserInfoDto.class)
        .flatMap(res -> {

            SignUpUserInfoDto userInfoDto = SignUpUserInfoDto.builder()
                .profile(res.getProperties().getProfile_image())
                .username(res.getProperties().getNickname())
                .provider(Provider.KAKAO)
                .providerId(String.valueOf(res.getId()))
                .email(res.getKakao_account().getEmail())
                .password("")
                .build();
          Long id = userService.signUp(userInfoDto);
          String jwtToken = jwtUtil.createJwt(id, 60 * 60 * 60 * 1000L);

            LoginDto loginDto = LoginDto.builder()
                .accessToken(jwtToken).build();
          return Mono.just(SuccessResponse.of(loginDto));
        })
        .onErrorResume(err -> {
            log.error(err.getMessage());
          throw CustomException.of(Error.INVALID_TOKEN_ERROR);
        });
  }

  @GetMapping("/apple")
  public SuccessResponse<LoginDto> appleLogin(String token, String username)
      throws IOException, ParseException, JOSEException {

    try {
        token = token.substring(7);
        Claims claims = jwtUtil.getAppleTokenClaims(token);
        SignUpUserInfoDto userInfoDto = SignUpUserInfoDto.builder()
            .profile(null)
            .username(username)
            .provider(Provider.APPLE)
            .providerId((String) claims.get("sub"))
            .email((String) claims.get("email"))
            .password("")
            .build();

        Long id = userService.signUp(userInfoDto);
        String jwtToken = jwtUtil.createJwt(id, 60 * 60 * 60 * 1000L);

        LoginDto loginDto = LoginDto.builder()
            .accessToken(jwtToken).build();
        return SuccessResponse.of(loginDto);
    } catch (Exception e) {
        log.error(e.getMessage());
        throw CustomException.of(Error.INVALID_TOKEN_ERROR);
    }
  }
}
