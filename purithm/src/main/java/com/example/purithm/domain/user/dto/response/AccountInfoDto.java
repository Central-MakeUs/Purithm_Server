package com.example.purithm.domain.user.dto.response;

import java.util.Date;

import com.example.purithm.domain.user.entity.Provider;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AccountInfoDto(
    @Schema(description = "소셜로그인 플랫폼")
    Provider provider,
    @Schema(description = "가입일")
    Date createdAt,
    @Schema(description = "이메일")
    String email
) {

}
