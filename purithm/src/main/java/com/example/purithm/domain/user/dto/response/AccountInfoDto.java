package com.example.purithm.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AccountInfoDto(
    @Schema(description = "소셜로그인 플랫폼")
    String provider,
    @Schema(description = "email")
    String email
) {

}
