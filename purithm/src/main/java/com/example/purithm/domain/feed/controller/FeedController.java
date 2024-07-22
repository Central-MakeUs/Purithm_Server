package com.example.purithm.domain.feed.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.global.response.SuccessResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feeds")
public class FeedController implements FeedControllerDocs {
  @GetMapping
  public SuccessResponse<List<FeedDto>> getFeeds(String authorization, String sortedBy) {
    return null;
  }
}
