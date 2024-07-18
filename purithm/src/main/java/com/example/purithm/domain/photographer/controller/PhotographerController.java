package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.photographer.response.PhotographerDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/photographers")
public class PhotographerController {
  @GetMapping
  @Operation(summary = "작가 정보를 최신순으로 조회합니다.")
  public SuccessResponse<PhotographerDto> getPhotographers() {
    return null;
  }
}
