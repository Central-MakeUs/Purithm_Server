package com.example.purithm.domain.feed.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeds")
public class FeedController implements FeedControllerDocs {
  @GetMapping
  public SuccessResponse<List<FeedDto>> getFeeds(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "sortedBy", required = false) String sortedBy) {
    return null;
  }
}
