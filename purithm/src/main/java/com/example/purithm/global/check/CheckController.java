package com.example.purithm.global.check;

import com.example.purithm.domain.user.service.UserService;
import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;
import com.example.purithm.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CheckController {

  private final UserService userService;

  @GetMapping
  public SuccessResponse healthCheck() {
    return SuccessResponse.of();
  }

  @GetMapping("/api")
  public SuccessResponse checkLoginUser(@LoginInfo Long id) {
    if (!userService.checkTerms(id)) {
      throw CustomException.of(Error.NOT_AGREED_TERM);
    }
    return SuccessResponse.of();
  }
}
