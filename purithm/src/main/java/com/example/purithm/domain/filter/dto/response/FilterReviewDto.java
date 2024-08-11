package com.example.purithm.domain.filter.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilterReviewDto(
	@Schema(description = "평균 퓨어지수")
	int avg,
	@Schema(description = "사용자가 필터를 열람한 적 있는지 여부")
	boolean hasViewed,
	@Schema(description = "리뷰")
	List<ReviewDto> reviews
) {
	public static FilterReviewDto of(int avg, boolean hasViewed, List<ReviewDto> reviews) {
		return FilterReviewDto.builder()
			.avg(avg)
			.hasViewed(hasViewed)
			.reviews(reviews)
			.build();
	}
}
