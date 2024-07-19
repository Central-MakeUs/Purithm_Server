package com.example.purithm.domain.filter.controller;

import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.filter.dto.response.PhotographerDescriptionDto;
import com.example.purithm.domain.filter.dto.response.ReviewDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filters")
public class FilterController {

  @GetMapping
  @Operation(summary = "메인 홈에서 간략한 필터 정보를 조회합니다.")
  public SuccessResponse<List<FilterDto>> getFilters(
      @RequestParam(value = "type", required = false) String type,
      @RequestParam(value = "sortedBy", required = false) String sortedBy
  ) {
    return null;
  }

  @GetMapping("/{filterId}")
  @Operation(summary = "필터 상세 정보를 조회합니다.")
  public SuccessResponse<FilterDetailDto> getFilterDetail(@PathVariable Long filterId) {
    return null;
  }

  @GetMapping("/{filterId}/photographer")
  @Operation(summary = "작가의 말을 조회합니다.")
  public SuccessResponse<PhotographerDescriptionDto> getPhotographerDescription(@PathVariable Long filterId) {
    return null;
  }

  @GetMapping("/{filterId}/reviews")
  @Operation(summary = "필터 상세에서 필터 리뷰들을 조회합니다.")
  public SuccessResponse<List<ReviewDto>> getReviews(@PathVariable Long filterId) {
    return null;
  }

  @PostMapping("/{filterId}/likes")
  @Operation(summary = "필터 좋아요를 누릅니다.")
  public SuccessResponse<Boolean> likes(@PathVariable Long filterId) {
    return null;
  }

  @DeleteMapping("/{filterId}/likes")
  @Operation(summary = "필터 좋아요를 취소합니다.")
  public SuccessResponse<Boolean> deleteLikes(@PathVariable Long filterId) {
    return null;
  }
}
