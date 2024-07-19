package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.global.response.SuccessResponse;
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
  public SuccessResponse<ReviewResponseDto> getReviewDetail(@PathVariable Long reviewId) {
    return null;
  }

  @PostMapping
  public SuccessResponse<Void> writeReview(@RequestBody ReviewResponseDto request) {
    return null;
  }
}
