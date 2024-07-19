package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.photographer.response.PhotographerDto;
import com.example.purithm.domain.photographer.response.PhotographerFilterDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photographers")
public class PhotographerController {
  @GetMapping
  @Operation(summary = "작가 정보를 조회합니다.")
  public SuccessResponse<List<PhotographerDto>> getPhotographers(
      @RequestParam(value = "sortedBy", required = false) String sortedBy
  ) {
    return null;
  }

  @GetMapping("/{photographerId}/filters")
  public SuccessResponse<List<PhotographerFilterDto>> getFiltersByPhotographer(
      @PathVariable Long photographerId,
      @RequestParam(value = "sortedBy", required = false) String sortedBy) {
    return null;
  }
}
