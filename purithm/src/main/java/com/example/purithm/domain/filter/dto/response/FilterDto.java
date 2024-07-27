package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record FilterDto(
    @Schema(description = "필터 id")
    Long id,
    @Schema(description = "필터 이름")
    String name,
    @Schema(description = "필터 썸네일")
    String thumbnail,
    @Schema(description = "작가 id")
    Long photographerId,
    @Schema(description = "작가 이름")
    String photographerName,
    @Schema(description = "좋아요 수")
    int likes

) {

}
