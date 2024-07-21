package com.example.purithm.domain.filter.controller;

import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.filter.dto.response.PhotographerDescriptionDto;
import com.example.purithm.domain.filter.dto.response.ReviewDto;
import com.example.purithm.global.response.ErrorResponse;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface FilterControllerDocs {
  @Operation(summary = "메인 홈에서 간략한 필터 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<List<FilterDto>> getFilters(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "type", required = false) String type,
      @RequestParam(value = "sortedBy", required = false) String sortedBy
  );

  @Operation(summary = "필터 상세 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 상세 정보 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<FilterDetailDto> getFilterDetail(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "작가의 말을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가의 말 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<PhotographerDescriptionDto> getPhotographerDescription(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "필터 상세에서 필터 리뷰들을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 리뷰 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<List<ReviewDto>> getReviews(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "필터 좋아요를 누릅니다.")
  @ApiResponse(responseCode = "200", description = "필터 좋아요 누르기 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<Boolean> likes(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "필터 좋아요를 취소합니다.")
  @ApiResponse(responseCode = "200", description = "필터 좋아요 취소하기 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<Boolean> deleteLikes(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);
}
