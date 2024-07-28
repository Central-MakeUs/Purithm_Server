package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.photographer.dto.response.PhotographerDto;
import com.example.purithm.domain.photographer.dto.response.PhotographerFilterDto;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface PhotographerControllerDocs {
  @Operation(summary = "작가 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가 조회 성공")
  public SuccessResponse<List<PhotographerDto>> getPhotographers(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
                  @ExampleObject(name = "오래된순", summary = "오래된순 정렬", value = "earliest"),
                  @ExampleObject(name = "퓨어지수 높은순", summary = "퓨어지수 높은순 정렬", value = "popular")}) String sortedBy
  );

  @Operation(summary = "특정 작가의 필터를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가의 필터 조회 성공")
  public SuccessResponse<List<PhotographerFilterDto>> getFiltersByPhotographer(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long photographerId,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
                  @ExampleObject(name = "오래된순", summary = "오래된순 정렬", value = "earliest"),
                  @ExampleObject(name = "퓨어지수 높은순", summary = "퓨어지수 높은순 정렬", value = "popular")}) String sortedBy);
}
