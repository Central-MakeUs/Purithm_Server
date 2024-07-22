package com.example.purithm.domain.review.controller;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.global.response.ErrorResponse;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ReviewControllerDocs {
  @Operation(summary = "리뷰 상세 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "리뷰 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<ReviewResponseDto> getReviewDetail(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long reviewId);

  @Operation(summary = "리뷰를 작성합니다.")
  @ApiResponse(responseCode = "200", description = "리뷰 작성 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<Void> writeReview(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestBody ReviewResponseDto request);
}
