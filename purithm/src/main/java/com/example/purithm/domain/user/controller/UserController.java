package com.example.purithm.domain.user.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.domain.filter.dto.response.DatedFilterDto;
import com.example.purithm.domain.filter.dto.response.DatedStampDto;
import com.example.purithm.domain.filter.dto.response.LikedFilterDto;
import com.example.purithm.domain.filter.service.FilterService;
import com.example.purithm.domain.review.service.ReviewService;
import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.UserInfoDto;
import com.example.purithm.domain.user.service.UserService;
import com.example.purithm.global.response.SuccessResponse;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

  private final UserService userService;
  private final FilterService filterService;
  private final ReviewService reviewService;

  @PostMapping("/terms")
  public SuccessResponse agreeTerm(Long id) {
    log.error(String.valueOf(id));
    userService.agreeToTermsOfUse(id);
    return SuccessResponse.of();
  }

  @GetMapping("/me")
  public SuccessResponse<UserInfoDto> getMyInfo(Long id) {
    return SuccessResponse.of(userService.getUserInfo(id));
  }

  @PostMapping("/me")
  public SuccessResponse changeProfile(Long id, UserInfoRequestDto userInfoRequestDto) {
    userService.updateProfile(userInfoRequestDto, id);
    return SuccessResponse.of();
  }

  @GetMapping("/account")
  public SuccessResponse<AccountInfoDto> getAccountInfo(Long id) {
    return SuccessResponse.of(userService.getUserAccountInfo(id));
  }

  @GetMapping("/stamps")
  public SuccessResponse<DatedStampDto> getStamp(Long id) {
    return SuccessResponse.of(reviewService.getStamps(id));
  }

  @GetMapping("/reviews")
  public SuccessResponse<List<FeedDto>> getMyReview(Long id) {
    return SuccessResponse.of(reviewService.getMyReviews(id));
  }

  @DeleteMapping("/reviews/{reviewId}")
  public SuccessResponse deleteReview(Long id, Long reviewId) {
    reviewService.deleteReview(id, reviewId);
    return SuccessResponse.of();
  }

  @GetMapping("/picks")
  public SuccessResponse<List<LikedFilterDto>> getMyPick(Long id) {
    return SuccessResponse.of(filterService.getLikedFilters(id));
  }

  @GetMapping("/history")
  public SuccessResponse<DatedFilterDto> getFilterViewHistory(Long id) {
    return SuccessResponse.of(filterService.getFilterViewHistory(id));
  }

  @DeleteMapping
  public SuccessResponse deleteUser(Long id) {
    userService.deleteUser(id);
    return SuccessResponse.of();
  }
}
