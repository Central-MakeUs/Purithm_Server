package com.example.purithm.global.auth.handler;

import com.example.purithm.global.auth.dto.response.LoginSuccessDto;
import com.example.purithm.global.auth.entity.CustomOAuth2User;
import com.example.purithm.global.auth.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final JWTUtil jwtUtil;

  public OAuth2AuthenticationSuccessHandler(JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    String token = jwtUtil.createJwt(oAuth2User.getName(), 60 * 60 * 60 * 1000L);

    response.setContentType("application/json");
    response.setStatus(response.SC_OK);
    LoginSuccessDto body = LoginSuccessDto.builder()
        .code(200).message("login success").token(token).build();

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(body);

    response.getOutputStream().println(json);
  }
}
