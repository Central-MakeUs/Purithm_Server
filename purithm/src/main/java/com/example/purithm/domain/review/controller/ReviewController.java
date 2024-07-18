package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.response.ReviewDto;
import com.example.purithm.global.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/reviews")
public class ReviewController {
  @GetMapping("/{reviewId}")
  public SuccessResponse<ReviewDto> getReviewDetail(@PathVariable Long reviewId) {
    return null;
  }
}
