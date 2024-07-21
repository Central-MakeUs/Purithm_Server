package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
  @GetMapping("/{reviewId}")
  @Operation(summary = "리뷰 상세 정보를 조회합니다.")
  public SuccessResponse<ReviewResponseDto> getReviewDetail(@PathVariable Long reviewId) {
    return null;
  }

  @PostMapping
  @Operation(summary = "리뷰를 작성합니다.")
  public SuccessResponse<Void> writeReview(@RequestBody ReviewResponseDto request) {
    return null;
  }
}
