package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.request.ReviewRequestDto;
import com.example.purithm.domain.review.dto.response.CreatedReviewDto;
import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.domain.review.service.ReviewService;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;
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
  public SuccessResponse<CreatedReviewDto> writeReview(Long id, ReviewRequestDto request) {
    if (request.pictures()==null || request.pictures().size()==0) {
      throw CustomException.of(Error.NO_REVIEW_CONTENT_ERROR);
    }
    if (request.content().length() < 20) {
      throw CustomException.of(Error.INVALID_REVIEW_CONTENT_ERROR);
    }
    return SuccessResponse.of(reviewService.writeReview(id, request));
  }
}
