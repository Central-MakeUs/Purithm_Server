package com.example.purithm.domain.photographer.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PhotographerFilterDto(
    @Schema(description = "필터 id")
    Long id,
    @Schema(description = "필터 이름")
    String name,
    @Schema(description = "필터 썸네일")
    String thumbnail,
    @Schema(description = "필터 좋아요 수")
    int likes
) {

}
