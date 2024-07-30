package com.example.purithm.global.check;

import com.example.purithm.global.auth.annotation.LoginInfo;
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
  public SuccessResponse checkLoginUser(@LoginInfo Long id) {
    return SuccessResponse.of();
  }
}
