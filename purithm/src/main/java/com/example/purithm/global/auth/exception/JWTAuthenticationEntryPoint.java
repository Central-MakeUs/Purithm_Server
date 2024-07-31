package com.example.purithm.global.auth.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    CustomException customException = CustomException.of(Error.INVALID_TOKEN_ERROR);
    String jsonResponse = objectMapper.writeValueAsString(customException);
    response.getWriter().write(jsonResponse);
  }
}
