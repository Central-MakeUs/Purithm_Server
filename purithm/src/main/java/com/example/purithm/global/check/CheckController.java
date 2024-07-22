package com.example.purithm.global.check;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

  @GetMapping("/api")
  public void checkToken() {
    return;
  }


}
