package com.example.purithm.domain.filter.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record FilterListDto(
	boolean isLast,
	int totalPage,
	long totalElement,
	List<FilterDto> filters
) {
}
