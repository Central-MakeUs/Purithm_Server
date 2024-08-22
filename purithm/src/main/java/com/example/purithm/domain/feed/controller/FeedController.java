package com.example.purithm.domain.feed.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.review.service.ReviewService;
import com.example.purithm.global.response.SuccessResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class FeedController implements FeedControllerDocs {

  private final ReviewService reviewService;

  @GetMapping
  public SuccessResponse<List<FeedDto>> getFeeds(Long id, OS os, String sortedBy) {
    return SuccessResponse.of(reviewService.getFeeds(os, sortedBy));
  }

  @PostMapping("/blocked-feed")
  public SuccessResponse blockFeed(Long id, Long feedId) {
    reviewService.blockUser(id, feedId);
    return SuccessResponse.of();
  }
}
