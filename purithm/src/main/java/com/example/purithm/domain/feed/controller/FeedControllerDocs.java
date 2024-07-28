package com.example.purithm.domain.feed.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface FeedControllerDocs {
  @ApiResponse(responseCode = "200", description = "OK")
  public SuccessResponse<List<FeedDto>> getFeeds(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
              @ExampleObject(name = "오래된순", summary = "오래된순 정렬", value = "earliest"),
              @ExampleObject(name = "퓨어지수 높은순", summary = "퓨어지수 높은순 정렬", value = "popular")}) String sortedBy
  );
}
