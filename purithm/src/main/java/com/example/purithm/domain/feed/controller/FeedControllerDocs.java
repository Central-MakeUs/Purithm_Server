package com.example.purithm.domain.feed.controller;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.global.response.ErrorResponse;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface FeedControllerDocs {
  @Operation(summary = "피드를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "피드 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<List<FeedDto>> getFeeds(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "sortedBy", required = false) String sortedBy
  );
}
