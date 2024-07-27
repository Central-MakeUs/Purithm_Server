package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

public record StampDto(
    @Schema(description = "스탬프 관련 필터 id")
    Long filterId,
    @Schema(description = "스탬프 관련 필터 이름")
    String name,
    @Schema(description = "스탬프 관련 필터 작가 이름")
    String photographer,
    @Schema(description = "스탬프 관련 필터 프로필")
    String profile,
    @Schema(description = "스탬프 관련 리뷰 생성일")
    Date createdAt
) {
}
