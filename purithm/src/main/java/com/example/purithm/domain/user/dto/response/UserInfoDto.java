package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserInfoDto(
    @Schema(description = "유저 id")
    Long id,
    @Schema(description = "유저 이름")
    String name,
    @Schema(description = "유저 프로필")
    String profile,
    @Schema(description = "스탬프 개수")
    int stamp
) {

}
