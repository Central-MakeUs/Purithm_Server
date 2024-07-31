package com.example.purithm.domain.filter.controller;

import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.filter.dto.response.PhotographerDescriptionDto;
import com.example.purithm.domain.filter.dto.response.ReviewDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.service.FilterService;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filters")
@RequiredArgsConstructor
public class FilterController implements FilterControllerDocs {

  private final FilterService filterService;

  @GetMapping
  public SuccessResponse<List<FilterDto>> getFilters(Long id, OS os, String tag, String sortedBy
  ) {
    return SuccessResponse.of(filterService.getFilters(os, tag, sortedBy));
  }

  @GetMapping("/{filterId}")
  public SuccessResponse<FilterDetailDto> getFilterDetail(String authorization, OS os, Long filterId) {
    return null;
  }

  @GetMapping("/{filterId}/photographer")
  public SuccessResponse<PhotographerDescriptionDto> getPhotographerDescription(String authorization, Long filterId) {
    return null;
  }

  @GetMapping("/{filterId}/reviews")
  public SuccessResponse<List<ReviewDto>> getReviews(String authorization, Long filterId) {
    return null;
  }

  @PostMapping("/{filterId}/likes")
  public SuccessResponse<Boolean> likes(String authorization, Long filterId) {
    return null;
  }

  @DeleteMapping("/{filterId}/likes")
  public SuccessResponse<Boolean> deleteLikes(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId) {
    return null;
  }
}
