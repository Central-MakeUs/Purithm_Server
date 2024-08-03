package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.domain.review.service.ReviewService;
import com.example.purithm.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

  private final ReviewService reviewService;

  @GetMapping("/{reviewId}")
  public SuccessResponse<ReviewResponseDto> getReviewDetail(Long id, Long reviewId) {
    return SuccessResponse.of(reviewService.getReview(reviewId));
  }

  @PostMapping
  public SuccessResponse<Void> writeReview(Long id, ReviewResponseDto request) {
    return null;
  }
}
