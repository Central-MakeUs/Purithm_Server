package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController implements ReviewControllerDocs {
  @GetMapping("/{reviewId}")
  public SuccessResponse<ReviewResponseDto> getReviewDetail(String authorization, Long reviewId) {
    return null;
  }

  @PostMapping
  public SuccessResponse<Void> writeReview(String authorization, ReviewResponseDto request) {
    return null;
  }
}
