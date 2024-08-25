package com.example.purithm.domain.filter.dto.response;

import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LikedFilterDto(
	@Schema(description = "필터 id")
	Long id,
	@Schema(description = "필터 멤버십 타입", example = "basic")
	Membership membership,
	@Schema(description = "필터 이름")
	String name,
	@Schema(description = "필터 썸네일")
	String thumbnail,
	@Schema(description = "작가 이름")
	String photographerName,
	@Schema(description = "좋아요 수")
	long likes,
	@Schema(description = "필터 접근 가능 여부")
	boolean canAccess,

	@Schema(description = "필터 OS 정보")
	OS os
) {
}
