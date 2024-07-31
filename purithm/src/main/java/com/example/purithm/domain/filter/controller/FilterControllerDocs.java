package com.example.purithm.domain.filter.controller;

import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.filter.dto.response.PhotographerDescriptionDto;
import com.example.purithm.domain.filter.dto.response.ReviewDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface FilterControllerDocs {
  @Operation(summary = "메인 홈에서 간략한 필터 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 조회 성공")
  public SuccessResponse<List<FilterDto>> getFilters(
      @LoginInfo Long id,
      @RequestParam(value = "os", required = true) @Parameter(description = "휴대폰 os",
        examples = {@ExampleObject(value = "AOS"), @ExampleObject(value = "iOS")}) OS os,
      @RequestParam(value = "tag", required = false) String tag,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
                  @ExampleObject(name = "오래된순", summary = "오래된순 정렬", value = "earliest"),
                  @ExampleObject(name = "퓨어지수 높은순", summary = "퓨어지수 높은순 정렬", value = "popular")}) String sortedBy
  );

  @Operation(summary = "필터 상세 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 상세 정보 조회 성공")
  @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)
  public SuccessResponse<FilterDetailDto> getFilterDetail(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @RequestParam(value = "os", required = true) @Parameter(description = "휴대폰 os",
          examples = {@ExampleObject(value = "AOS"), @ExampleObject(value = "iOS")}) OS os,
      @PathVariable Long filterId);

  @Operation(summary = "작가의 말을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가의 말 조회 성공")
  public SuccessResponse<PhotographerDescriptionDto> getPhotographerDescription(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "필터 상세에서 필터 리뷰들을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 리뷰 조회 성공")
  public SuccessResponse<List<ReviewDto>> getReviews(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "필터 좋아요를 누릅니다.")
  @ApiResponse(responseCode = "200", description = "필터 좋아요 누르기 성공")
  public SuccessResponse<Boolean> likes(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);

  @Operation(summary = "필터 좋아요를 취소합니다.")
  @ApiResponse(responseCode = "200", description = "필터 좋아요 취소하기 성공")
  public SuccessResponse<Boolean> deleteLikes(
      @RequestHeader(value = "Authorization") @Parameter(description = "인증 토큰") String authorization,
      @PathVariable Long filterId);
}
