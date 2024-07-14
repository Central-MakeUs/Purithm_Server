package com.example.purithm.user.handler;

import com.example.purithm.user.entity.CustomOAuth2User;
import com.example.purithm.user.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JWTUtil jwtUtil;

  public OAuth2AuthenticationSuccessHandler(JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    String token = jwtUtil.createJwt(oAuth2User.getName(), 60*60*60L);

    Cookie cookie = new Cookie("Authorization", token);
    cookie.setMaxAge(60*60*60);
    cookie.setPath("/api");
    cookie.setHttpOnly(true);

    response.addCookie(cookie);
    response.sendRedirect("/");
  }
}
