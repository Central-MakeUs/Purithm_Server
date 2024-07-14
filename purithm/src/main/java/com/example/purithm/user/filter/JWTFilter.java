package com.example.purithm.user.filter;

import com.example.purithm.user.entity.CustomOAuth2User;
import com.example.purithm.user.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;

  public  JWTFilter(JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = request.getHeader("Authorization");

      if (token == null || !token.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
      }

      token = token.substring(7);
      if (jwtUtil.isExpired(token)) {
        filterChain.doFilter(request, response);
        return;
      }

      String username = jwtUtil.getUsername(token);

      CustomOAuth2User oAuth2User = new CustomOAuth2User(username);
      Authentication authToken = new UsernamePasswordAuthenticationToken(oAuth2User, null, null);
      SecurityContextHolder.getContext().setAuthentication(authToken);
    } catch (Exception e) {
      request.setAttribute("exception", e);
    }

    filterChain.doFilter(request, response);
  }
}
