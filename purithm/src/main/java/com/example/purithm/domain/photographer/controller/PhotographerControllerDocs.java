package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.photographer.response.PhotographerDto;
import com.example.purithm.domain.photographer.response.PhotographerFilterDto;
import com.example.purithm.global.response.ErrorResponse;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface PhotographerControllerDocs {
  @Operation(summary = "작가 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<List<PhotographerDto>> getPhotographers(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "sortedBy", required = false) String sortedBy
  );

  @Operation(summary = "특정 작가의 필터를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가의 필터 조회 성공",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = SuccessResponse.class)))
  @ApiResponse(responseCode = "401", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(responseCode = "404", description = "유저 인증 실패",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)))
  public SuccessResponse<List<PhotographerFilterDto>> getFiltersByPhotographer(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long photographerId,
      @RequestParam(value = "sortedBy", required = false) String sortedBy);
}
