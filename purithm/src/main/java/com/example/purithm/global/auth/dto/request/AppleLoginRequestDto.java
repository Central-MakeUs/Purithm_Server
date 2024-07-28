package com.example.purithm.global.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record AppleLoginRequestDto(
    @Schema(description = "토큰")
    String token,
    @Schema(description = "이메일")
    String email
) {

}
