package com.example.purithm.domain.user.controller;

import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.MyFilterDto;
import com.example.purithm.domain.user.dto.response.MyPickDto;
import com.example.purithm.domain.user.dto.response.MyReviewDto;
import com.example.purithm.domain.user.dto.response.StampDto;
import com.example.purithm.domain.user.dto.response.UserInfoDto;
import com.example.purithm.global.response.ErrorResponse;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface UserControllerDocs {
  @Operation(summary = "내 프로필을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 프로필 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<UserInfoDto> getMyInfo(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  );

  @Operation(summary = "내 프로필을 수정합니다.")
  @ApiResponse(responseCode = "200", description = "내 프로필 수정 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<Void> changeProfile(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestBody UserInfoRequestDto userInfoRequestDto);

  @Operation(summary = "내 계정 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 계정 정보 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<AccountInfoDto> getAccountInfo(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  );

  @Operation(summary = "내 스탬프를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 스탬프 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<List<StampDto>> getStamp(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  );

  @Operation(summary = "내 필터를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 필터 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<List<MyFilterDto>> getMyFilter(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  );

  @Operation(summary = "내 필터를 삭제합니다.")
  @ApiResponse(responseCode = "200", description = "내 필터 삭제 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<Void> deleteFilter(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "내 리뷰를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 리뷰 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<List<MyReviewDto>> getMyReview(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  );

  @Operation(summary = "내 리뷰를 삭제합니다.")
  @ApiResponse(responseCode = "200", description = "내 리뷰 삭제 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<Void> deleteReview(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long reviewId);

  @Operation(summary = "내 찜 목록을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 찜 목록 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  public SuccessResponse<List<MyPickDto>> getMyPick(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  );
}
