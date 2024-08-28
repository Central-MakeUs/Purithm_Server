package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.filter.dto.response.FilterListDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.photographer.dto.response.PhotographerDto;
import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface PhotographerControllerDocs {
  @Operation(summary = "작가 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가 조회 성공")
  public SuccessResponse<List<PhotographerDto>> getPhotographers(
      @LoginInfo Long id,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
                  @ExampleObject(name = "오래된순", summary = "오래된순 정렬", value = "earliest"),
                  @ExampleObject(name = "필터 많은순", summary = "필터 많은순", value = "filter")}) String sortedBy
  );

  @Operation(summary = "특정 작가 상세 정보를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가 조회 성공")
  public SuccessResponse<PhotographerDto> getPhotographerInfo(@LoginInfo Long id, @PathVariable Long photographerId);

  @Operation(summary = "특정 작가의 필터를 조회합니다.")
  @ApiResponse(responseCode = "200", description = "작가의 필터 조회 성공")
  public SuccessResponse<FilterListDto> getFiltersByPhotographer(
      @LoginInfo Long id,
      @PathVariable Long photographerId,
      @RequestParam(value = "sortedBy", required = false) @Parameter(description = "정렬순",
          examples =
              {@ExampleObject(name = "최신순", summary = "최신순 정렬", value = "latest"),
                  @ExampleObject(name = "퓨어지수 높은순", summary = "퓨어지수 높은순 정렬", value = "pure"),
                  @ExampleObject(name = "조회순", summary = "조회순 정렬", value = "views"),
                  @ExampleObject(name = "이름순", summary = "이름순 정렬", value = "name")}) String sortedBy,
      @RequestParam(value = "os", required = true) @Parameter(description = "휴대폰 os",
          examples = {@ExampleObject(value = "AOS"), @ExampleObject(value = "iOS")}) OS os,
      @RequestParam(value = "page") int page,
      @RequestParam(value = "size") int size);
}
