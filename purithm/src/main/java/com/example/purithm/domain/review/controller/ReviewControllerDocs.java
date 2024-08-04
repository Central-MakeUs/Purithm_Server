package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.request.ReviewRequestDto;
import com.example.purithm.domain.review.dto.response.CreatedReviewDto;
import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReviewControllerDocs {
  @Operation(summary = "리뷰 상세 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "리뷰 조회 성공")
  public SuccessResponse<ReviewResponseDto> getReviewDetail(@LoginInfo Long id, @PathVariable Long reviewId);

  @Operation(summary = "리뷰를 작성합니다.")
  @ApiResponse(responseCode = "200", description = "리뷰 작성 성공")
  public SuccessResponse<CreatedReviewDto> writeReview(@LoginInfo Long id, @RequestBody ReviewRequestDto request);
}
