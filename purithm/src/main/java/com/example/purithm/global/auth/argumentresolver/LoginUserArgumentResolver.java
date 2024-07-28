package com.example.purithm.global.auth.argumentresolver;

import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.auth.entity.CustomOAuth2User;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(LoginInfo.class) != null;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();

    if (user.getName().equals("anonymousUser")) {
      throw CustomException.of(Error.INVALID_TOKEN_ERROR);
    }

    return user;
  }
}
