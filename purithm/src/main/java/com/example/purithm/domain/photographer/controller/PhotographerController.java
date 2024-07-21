package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.photographer.response.PhotographerDto;
import com.example.purithm.domain.photographer.response.PhotographerFilterDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photographers")
public class PhotographerController implements PhotographerControllerDocs {
  @GetMapping
  public SuccessResponse<List<PhotographerDto>> getPhotographers(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "sortedBy", required = false) String sortedBy
  ) {
    return null;
  }

  @GetMapping("/{photographerId}/filters")
  public SuccessResponse<List<PhotographerFilterDto>> getFiltersByPhotographer(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long photographerId,
      @RequestParam(value = "sortedBy", required = false) String sortedBy) {
    return null;
  }
}
