package com.example.purithm.domain.filter.dto.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.repository.projection.ViewHistoryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DatedFilterDto {
	@Schema(description = "총 개수")
	int totalCount;

	@Schema(description = "필터 리스트")
	List<GroupedFilterLogDto> list;

	public static DatedFilterDto of(List<ViewHistoryProjection> result) {
		List<FilterLogDto> filterLogs = FilterLogDto.of(result);
		List<GroupedFilterLogDto> groupedFilters = GroupedFilterLogDto.groupByDate(filterLogs);

		return DatedFilterDto.builder()
			.totalCount(filterLogs.size())
			.list(groupedFilters)
			.build();
	}
}


@Getter
@Builder
class GroupedFilterLogDto {
	String date;
	List<FilterLogDto> filters;

	public static List<GroupedFilterLogDto> groupByDate(List<FilterLogDto> filterLogs) {
		Map<String, List<FilterLogDto>> groupedMap = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		for(FilterLogDto filterLog : filterLogs) {
			String date = formatter.format(filterLog.getCreatedAt());
			groupedMap.computeIfAbsent(date, k -> new ArrayList<>()).add(filterLog);
		}

		List<GroupedFilterLogDto> groupedFilters = new ArrayList<>();
		for (Map.Entry<String, List<FilterLogDto>> entry : groupedMap.entrySet()) {
			GroupedFilterLogDto groupedFilter = GroupedFilterLogDto.builder()
				.date(entry.getKey())
				.filters(entry.getValue())
				.build();
			groupedFilters.add(groupedFilter);
		}

		Collections.sort(groupedFilters, (s1, s2) -> s1.getDate().compareTo(s2.getDate()));
		return groupedFilters;
	}
}

@Getter
@Builder
class FilterLogDto {
	@Schema(description = "필터 id")
	Long filterId;
	@Schema(description = "필터 이름")
	String filterName;
	@Schema(description = "필터 썸네일")
	String thumbnail;
	@Schema(description = "작가 이름")
	String photographer;
	@Schema(description = "필터 멤버십")
	Membership membership;
	@Schema(description = "필터 열람일")
	Date createdAt;
	@Schema(description = "해당 필터에 작성한 리뷰가 있는지 여부")
	boolean hasReview;
	@Schema(description = "리뷰 id", nullable = true)
	Long reviewId;
	@Schema(description = "열람 정보 관련 필터 OS 정보")
	OS os;

	public static List<FilterLogDto> of(List<ViewHistoryProjection> result) {
		return result.stream().map(res ->
			FilterLogDto.builder()
				.filterId(res.getFilterId())
				.filterName(res.getFilterName())
				.thumbnail(res.getThumbnail())
				.photographer(res.getPhotographer())
				.membership(res.getMembership())
				.createdAt(res.getCreatedAt())
				.hasReview(res.getReviewId() != null ? true : false)
				.reviewId(res.getReviewId())
				.os(res.getOs())
				.build()
		).toList();
	}
}