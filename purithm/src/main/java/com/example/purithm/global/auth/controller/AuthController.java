package com.example.purithm.global.auth.controller;

import com.example.purithm.global.auth.dto.response.KakaoUserInfoResponseDto;
import com.example.purithm.global.auth.dto.response.LoginResponseDto;
import com.example.purithm.global.auth.dto.response.SocialUserInfoDto;
import com.example.purithm.global.auth.jwt.JWTUtil;
import com.example.purithm.global.config.WebClientConfig;
import com.example.purithm.domain.user.service.UserService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
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

          SocialUserInfoDto userInfoDto = SocialUserInfoDto.builder()
              .profile(res.getProperties().getProfile_image())
              .nickname(res.getProperties().getNickname())
              .username("KAKAO " + res.getId())
              .build();
          String username = userService.signUp(userInfoDto);
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

  @GetMapping("/apple")
  public LoginResponseDto appleLogin(@RequestHeader("Authorization") String token)
      throws IOException, ParseException, JOSEException {
    URL jwkSetURL = new URL("https://appleid.apple.com/auth/keys");
    JWKSet jwkSet = JWKSet.load(jwkSetURL);

    token = token.substring(7);
    SignedJWT signedJWT = SignedJWT.parse(token);
    JWSHeader header = signedJWT.getHeader();
    RSAKey rsaKey = (RSAKey) jwkSet.getKeyByKeyId(header.getKeyID());

    if (rsaKey == null) {
      throw new RuntimeException("Unable to find key with kid: " + header.getKeyID());
    }

    RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
    Claims claims = Jwts.parser()
        .verifyWith(publicKey)
        .build()
        .parseClaimsJws(token)
        .getBody();

    SocialUserInfoDto userInfoDto = SocialUserInfoDto.builder()
        .nickname((String) claims.get("nickname"))
        .username("APPLE " + (String) claims.get("sub"))
        .profile(null)
        .build();


    String username = userService.signUp(userInfoDto);
    String jwtToken = jwtUtil.createJwt(username, 60 * 60 * 60 * 1000L);

    return LoginResponseDto.builder()
        .code(200).message("login success").token(jwtToken).build();
  }
}
