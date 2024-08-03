package com.example.purithm.domain.filter.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilterReviewDto(
	@Schema(description = "평균 퓨어지수")
	int avg,
	@Schema(description = "리뷰")
	List<ReviewDto> reviews
) {
	public static FilterReviewDto of(int avg, List<ReviewDto> reviews) {
		return FilterReviewDto.builder()
			.avg(avg)
			.reviews(reviews)
			.build();
	}
}
