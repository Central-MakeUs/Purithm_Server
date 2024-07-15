package com.example.purithm.auth.dto.response;

import lombok.Builder;

@Builder
public record LoginSuccessResponseDto(
    int code,
    String message,
    String token
) {
}
