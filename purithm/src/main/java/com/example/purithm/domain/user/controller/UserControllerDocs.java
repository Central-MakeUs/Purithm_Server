package com.example.purithm.domain.user.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.MyPickDto;
import com.example.purithm.domain.user.dto.response.StampDto;
import com.example.purithm.domain.user.dto.response.UserInfoDto;
import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerDocs {
  @Operation(summary = "내 프로필을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 프로필 조회 성공")
  public SuccessResponse<UserInfoDto> getMyInfo(@LoginInfo Long id);

  @Operation(summary = "내 프로필을 수정합니다.")
  @ApiResponse(responseCode = "200", description = "내 프로필 수정 성공")
  public SuccessResponse<Void> changeProfile(
      @LoginInfo Long id,
      @RequestBody UserInfoRequestDto userInfoRequestDto);

  @Operation(summary = "내 계정 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 계정 정보 조회 성공")
  public SuccessResponse<AccountInfoDto> getAccountInfo(@LoginInfo Long id);

  @Operation(summary = "내 스탬프를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 스탬프 조회 성공")
  public SuccessResponse<List<StampDto>> getStamp(@LoginInfo Long id);

  @Operation(summary = "내 리뷰를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 리뷰 조회 성공")
  public SuccessResponse<List<FeedDto>> getMyReview(@LoginInfo Long id);

  @Operation(summary = "내 리뷰를 삭제합니다.")
  @ApiResponse(responseCode = "200", description = "내 리뷰 삭제 성공")
  public SuccessResponse<Void> deleteReview(
      @LoginInfo Long id, @PathVariable Long reviewId);

  @Operation(summary = "내 찜 목록을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "내 찜 목록 조회 성공")
  public SuccessResponse<List<MyPickDto>> getMyPick(@LoginInfo Long id);
}
