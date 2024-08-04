package com.example.purithm.global.aws.dto;

import lombok.Builder;

@Builder
public record PresignedUrlDto(
	String url
) {
}
