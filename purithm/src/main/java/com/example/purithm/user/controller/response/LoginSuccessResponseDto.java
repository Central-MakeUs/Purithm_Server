package com.example.purithm.user.controller.response;

import lombok.Builder;

@Builder
public record LoginSuccessResponseDto(
    int code,
    String message,
    String token
) {
}
