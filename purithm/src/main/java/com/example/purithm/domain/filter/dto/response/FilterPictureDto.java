package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilterPictureDto(
	@Schema(description = "보정 사진")
	String picture,
	@Schema(description = "원본 사진")
	String originalPicture
) {
}
