package com.example.purithm.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserInfoRequestDto(
    @Schema(description = "사용자 이름")
    String name,
    @Schema(description = "사용자 프로필")
    String profile
) {

}
