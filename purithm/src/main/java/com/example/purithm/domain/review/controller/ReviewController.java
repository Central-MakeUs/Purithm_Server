package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {
  @GetMapping("/{reviewId}")
  public SuccessResponse<ReviewResponseDto> getReviewDetail(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long reviewId) {
    return null;
  }

  @PostMapping
  public SuccessResponse<Void> writeReview(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestBody ReviewResponseDto request) {
    return null;
  }
}
