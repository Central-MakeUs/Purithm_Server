package com.example.purithm.domain.filter.dto.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.purithm.domain.filter.entity.Membership;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FilterViewHistoryDto {
	@Schema(description = "총 개수")
	int totalCount;

	@Schema(description = "필터 리스트")
	List<GroupedFilter> list;

	public static FilterViewHistoryDto of(List<Object[]> result) {
		List<FilterLogDto> filterLogs = FilterLogDto.of(result);
		List<GroupedFilter> groupedFilters = GroupedFilter.groupByDate(filterLogs);

		return FilterViewHistoryDto.builder()
			.totalCount(filterLogs.size())
			.list(groupedFilters)
			.build();
	}
}

@Getter
@Builder
class GroupedFilter {
	String date;
	List<FilterLogDto> filterLogs;

	public static List<GroupedFilter> groupByDate(List<FilterLogDto> filterLogs) {
		Map<String, List<FilterLogDto>> groupedMap = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		for(FilterLogDto filterLog : filterLogs) {
			String date = formatter.format(filterLog.getCreatedAt());
			groupedMap.computeIfAbsent(date, k -> new ArrayList<>()).add(filterLog);
		}

		List<GroupedFilter> groupedFilters = new ArrayList<>();
		for (Map.Entry<String, List<FilterLogDto>> entry : groupedMap.entrySet()) {
			GroupedFilter groupedFilter = GroupedFilter.builder()
				.date(entry.getKey())
				.filterLogs(entry.getValue())
				.build();
			groupedFilters.add(groupedFilter);
		}

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

	public static List<FilterLogDto> of(List<Object[]> result) {
		return result.stream().map(res ->
			FilterLogDto.builder()
				.filterId((Long)res[0])
				.filterName((String)res[1])
				.photographer((String)res[2])
				.membership((Membership)res[3])
				.createdAt((Date)res[4])
				.hasReview(res[5] != null ? true : false)
				.reviewId((Long)res[5])
				.build()
		).toList();
	}
}
