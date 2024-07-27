package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MyPickDto(
    @Schema(description = "내 찜 목록 필터 id")
    Long id,
    @Schema(description = "내 찜 목록 필터 이름")
    String name,
    @Schema(description = "내 찜 목록 필터 사진")
    String picture,
    @Schema(description = "내 찜 목록 필터 작가")
    String photographer,
    @Schema(description = "내 찜 목록 필터 퓨어 지수")
    Long pureDegree
) {

}
