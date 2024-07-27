package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;

public record MyReviewDto(
    @Schema(description = "내가 리뷰 남긴 필터 id")
    Long id,
    @Schema(description = "내가 남긴 리뷰 내용")
    String content,
    @Schema(description = "내가 리뷰 남긴 필터 작가")
    String photographer,
    @Schema(description = "리뷰 생성일")
    Date createdAt,
    @Schema(description = "내가 리뷰 남긴 필터 작가 프로필")
    String photographerProfile,
    @Schema(description = "내가 리뷰 남긴 필터 사진들")
    List<String> pictures,
    @Schema(description = "퓨어 지수")
    int pureDegree
) {

}
