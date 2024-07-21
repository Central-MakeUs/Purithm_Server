package com.example.purithm.domain.user.controller;

import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.MyFilterDto;
import com.example.purithm.domain.user.dto.response.MyPickDto;
import com.example.purithm.domain.user.dto.response.MyReviewDto;
import com.example.purithm.domain.user.dto.response.StampDto;
import com.example.purithm.domain.user.dto.response.UserInfoDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs {

  @GetMapping("/me")
  public SuccessResponse<UserInfoDto> getMyInfo(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization) {
    return null;
  }

  @PostMapping("/me")
  public SuccessResponse<Void> changeProfile(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestBody UserInfoRequestDto userInfoRequestDto) {
    return null;
  }

  @GetMapping("/account")
  public SuccessResponse<AccountInfoDto> getAccountInfo(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization) {
    return null;
  }

  @GetMapping("/stamps")
  public SuccessResponse<List<StampDto>> getStamp(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization) {
    return null;
  }

  @GetMapping("/filters")
  public SuccessResponse<List<MyFilterDto>> getMyFilter(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization) {
    return null;
  }

  @DeleteMapping("/filters/{filterId}")
  public SuccessResponse<Void> deleteFilter(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId) {
    return null;
  }

  @GetMapping("/reviews")
  public SuccessResponse<List<MyReviewDto>> getMyReview(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization) {
    return null;
  }

  @DeleteMapping("/reviews/{reviewId}")
  public SuccessResponse<Void> deleteReview(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long reviewId) {
    return null;
  }

  @GetMapping("/picks")
  public SuccessResponse<List<MyPickDto>> getMyPick(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization
  ) {
    return null;
  }
}
