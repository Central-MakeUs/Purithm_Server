package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Date;

import com.example.purithm.domain.filter.entity.Membership;

@Builder
public record StampDto(
    @Schema(description = "스탬프 관련 필터 id")
    Long filterId,
    @Schema(description = "스탬프 관련 필터 이름")
    String filterName,
    @Schema(description = "스탬프 관련 필터 작가 이름")
    String photographer,
    @Schema(description = "스탬프 관련 필터 프로필")
    String thumbnail,
    @Schema(description = "스탬프 관련 리뷰 생성일")
    Date createdAt,
    @Schema(description = "스탬프 관련 필터 등급")
    Membership membership,
    @Schema(description = "리뷰 id")
    Long reviewId
) {
}
