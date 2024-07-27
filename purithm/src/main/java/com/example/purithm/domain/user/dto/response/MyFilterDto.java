package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MyFilterDto(
    @Schema(description = "필터 타입", example = "basic")
    String type,
    @Schema(description = "필터 이름")
    String name,
    @Schema(description = "작가 이름")
    String photographer,
    @Schema(description = "리뷰 id")
    Long reviewId
) {

}
