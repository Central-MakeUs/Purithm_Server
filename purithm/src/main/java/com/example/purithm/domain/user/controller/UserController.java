package com.example.purithm.domain.user.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.domain.filter.dto.response.FilterViewHistoryDto;
import com.example.purithm.domain.filter.dto.response.LikedFilterDto;
import com.example.purithm.domain.filter.service.FilterService;
import com.example.purithm.domain.review.service.ReviewService;
import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;
import com.example.purithm.domain.user.dto.response.AccountInfoDto;
import com.example.purithm.domain.user.dto.response.StampDto;
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

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

  private final UserService userService;
  private final FilterService filterService;
  private final ReviewService reviewService;

  @PostMapping("/terms")
  public SuccessResponse agreeTerm(Long id) {
    userService.agreeToTermsOfUse(id);
    return SuccessResponse.of();
  }

  @GetMapping("/me")
  public SuccessResponse<UserInfoDto> getMyInfo(Long id) {
    return SuccessResponse.of(userService.getUserInfo(id));
  }

  @PostMapping("/me")
  public SuccessResponse<Void> changeProfile(Long id, UserInfoRequestDto userInfoRequestDto) {
    return null;
  }

  @GetMapping("/account")
  public SuccessResponse<AccountInfoDto> getAccountInfo(Long id) {
    return SuccessResponse.of(userService.getUserAccountInfo(id));
  }

  @GetMapping("/stamps")
  public SuccessResponse<List<StampDto>> getStamp(Long id) {
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
  public SuccessResponse<List<FilterViewHistoryDto>> getFilterViewHistory(Long id) {
    return SuccessResponse.of(filterService.getFilterViewHistory(id));
  }
}
