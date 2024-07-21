package com.example.purithm.domain.user.controller;

import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.MyFilterDto;
import com.example.purithm.domain.user.dto.response.MyPickDto;
import com.example.purithm.domain.user.dto.response.MyReviewDto;
import com.example.purithm.domain.user.dto.response.StampDto;
import com.example.purithm.domain.user.dto.response.UserInfoDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @GetMapping("/me")
  @Operation(summary = "내 프로필을 조회합니다.")
  public SuccessResponse<UserInfoDto> getMyInfo() {
    return null;
  }

  @PostMapping("/me")
  @Operation(summary = "내 프로필을 수정합니다.")
  public SuccessResponse<Void> changeProfile(@RequestBody UserInfoRequestDto userInfoRequestDto) {
    return null;
  }

  @GetMapping("/account")
  @Operation(summary = "내 계정정보를 조회합니다.")
  public SuccessResponse<AccountInfoDto> getAccountInfo() {
    return null;
  }

  @GetMapping("/stamps")
  @Operation(summary = "내 스탬프를 조회합니다.")
  public SuccessResponse<List<StampDto>> getStamp() {
    return null;
  }

  @GetMapping("/filters")
  @Operation(summary = "내 필터를 조회합니다.")
  public SuccessResponse<List<MyFilterDto>> getMyFilter() {
    return null;
  }

  @DeleteMapping("/filters/{filterId}")
  @Operation(summary = "내 필터를 삭제합니다.")
  public SuccessResponse<Void> deleteFilter(@PathVariable Long filterId) {
    return null;
  }

  @GetMapping("/reviews")
  @Operation(summary = "내 리뷰를 조회합니다.")
  public SuccessResponse<List<MyReviewDto>> getMyReview() {
    return null;
  }

  @DeleteMapping("/reviews/{reviewId}")
  @Operation(summary = "내 리뷰를 삭제합니다.")
  public SuccessResponse<Void> deleteReview(@PathVariable Long reviewId) {
    return null;
  }

  @GetMapping("/picks")
  @Operation(summary = "내 찜 목록을 조회합니다.")
  public SuccessResponse<List<MyPickDto>> getMyPick() {
    return null;
  }
}
