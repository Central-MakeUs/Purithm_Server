package com.example.purithm.domain.filter.controller;

import com.example.purithm.domain.filter.dto.response.FilterDescriptionDto;
import com.example.purithm.domain.filter.dto.response.filterDetailValue.AOSFilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterListDto;
import com.example.purithm.domain.filter.dto.response.FilterReviewDto;
import com.example.purithm.domain.filter.dto.response.filterDetailValue.IOSFilterDetailDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.entity.Tag;
import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface FilterControllerDocs {
  @Operation(summary = "메인 홈에서 간략한 필터 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 조회 성공")
  public SuccessResponse<FilterListDto> getFilters(
      @LoginInfo Long id,
      @RequestParam(value = "os", required = true) @Parameter(description = "휴대폰 os",
        examples = {@ExampleObject(value = "AOS"), @ExampleObject(value = "iOS")}) OS os,
      @RequestParam(value = "tag", required = false) Tag tag,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
                  @ExampleObject(name = "오래된순", summary = "오래된순 정렬", value = "earliest"),
                  @ExampleObject(name = "퓨어지수 높은순", summary = "퓨어지수 높은순 정렬", value = "popular")}) String sortedBy,
      @RequestParam(value = "page") int page,
      @RequestParam(value = "size") int size
  );

  @Operation(summary = "필터 상세 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 상세 정보 조회 성공")
  public SuccessResponse<FilterDetailDto> getFilterDetail(
      @LoginInfo Long id,
      @PathVariable Long filterId);

  @Operation(summary = "AOS 필터값를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터값 조회 성공")
  public SuccessResponse<AOSFilterDetailDto> getAOSFilter(
      @LoginInfo Long id,
      @PathVariable Long filterId);

  @Operation(summary = "iOS 필터값를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터값 조회 성공")
  public SuccessResponse<IOSFilterDetailDto> getIOSFilter(
      @LoginInfo Long id,
      @PathVariable Long filterId);

  @Operation(summary = "필터 상세에서 필터 리뷰들을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 리뷰 조회 성공")
  public SuccessResponse<FilterReviewDto> getReviews(@LoginInfo Long id, @PathVariable Long filterId);

  @Operation(summary = "필터 좋아요를 누릅니다.")
  @ApiResponse(responseCode = "200", description = "필터 좋아요 누르기 성공")
  public SuccessResponse<Boolean> likes(
      @LoginInfo Long id,
      @PathVariable Long filterId);

  @Operation(summary = "필터 좋아요를 취소합니다.")
  @ApiResponse(responseCode = "200", description = "필터 좋아요 취소하기 성공")
  public SuccessResponse<Boolean> deleteLikes(
      @LoginInfo Long id,
      @PathVariable Long filterId);

  @Operation(summary = "필터 상세에서 필터 소개를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "필터 소개 조회 성공")
  public SuccessResponse<FilterDescriptionDto> getDescriptions(
      @LoginInfo Long id,
      @PathVariable Long filterId);
}
