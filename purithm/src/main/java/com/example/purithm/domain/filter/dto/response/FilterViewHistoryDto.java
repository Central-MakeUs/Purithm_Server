package com.example.purithm.domain.filter.dto.response;

import java.util.Date;

import com.example.purithm.domain.filter.entity.Membership;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilterViewHistoryDto(
	@Schema(description = "필터 id")
	Long filterId,
	@Schema(description = "필터 이름")
	String filterName,
	@Schema(description = "작가 이름")
	String photographer,
	@Schema(description = "필터 멤버십")
	Membership membership,
	@Schema(description = "필터 열람일")
	Date createdAt,
	@Schema(description = "해당 필터에 작성한 리뷰가 있는지 여부")
	boolean hasReview,
	@Schema(description = "리뷰 id", nullable = true)
	Long reviewId
) {
}
