package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserInfoDto(
    @Schema(description = "유저 id")
    Long id,
    @Schema(description = "유저 이름")
    String name,
    @Schema(description = "유저 프로필")
    String profile,
    @Schema(description = "스탬프 개수")
    int stamp,
    @Schema(description = "찜 목록 개수")
    int likes,
    @Schema(description = "필터 열람 내역 개수")
    int filterViewCount,
    @Schema(description = "남긴 후기 개수")
    int reviews
) {

}
