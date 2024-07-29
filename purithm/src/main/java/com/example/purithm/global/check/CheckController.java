package com.example.purithm.global.check;

import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.auth.entity.CustomOAuth2User;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;
import com.example.purithm.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

  @GetMapping
  public SuccessResponse healthCheck() {
    return SuccessResponse.of();
  }

  @GetMapping("/api")
  public SuccessResponse checkLoginUser(@LoginInfo CustomOAuth2User user) {
    if (user == null) {
      throw CustomException.of(Error.INVALID_TOKEN_ERROR);
    }
    return SuccessResponse.of();
  }
}
