package com.example.purithm.domain.filter.dto.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.photographer.entity.Photographer;
import com.example.purithm.domain.review.entity.Review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DatedStampDto {
	@Schema(description = "총 개수")
	int totalCount;

	@Schema(description = "필터 리스트")
	List<GroupedStampDto> list;

	public static DatedStampDto of(List<Review> reviews) {
		List<StampDto> stamps = StampDto.of(reviews);
		List<GroupedStampDto> groupedStamps = GroupedStampDto.groupByDate(stamps);

		return DatedStampDto.builder()
			.totalCount(stamps.size())
			.list(groupedStamps)
			.build();
	}
}

@Getter
@Builder
class GroupedStampDto {
	String date;
	List<StampDto> stamps;

	public static List<GroupedStampDto> groupByDate(List<StampDto> stampDtos) {
		Map<String, List<StampDto>> groupedMap = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		for(StampDto stampDto : stampDtos) {
			String date = formatter.format(stampDto.getCreatedAt());
			groupedMap.computeIfAbsent(date, k -> new ArrayList<>()).add(stampDto);
		}

		List<GroupedStampDto> groupedStamps = new ArrayList<>();
		for (Map.Entry<String, List<StampDto>> entry : groupedMap.entrySet()) {
			GroupedStampDto groupedStamp = GroupedStampDto.builder()
				.date(entry.getKey())
				.stamps(entry.getValue())
				.build();
			groupedStamps.add(groupedStamp);
		}

		Collections.sort(groupedStamps, (s1, s2) -> s2.getDate().compareTo(s1.getDate()));
		return groupedStamps;
	}
}

@Getter
@Builder
class StampDto {
	@Schema(description = "스탬프 관련 필터 id")
	Long filterId;
	@Schema(description = "스탬프 관련 필터 이름")
	String filterName;
	@Schema(description = "스탬프 관련 필터 작가 이름")
	String photographer;
	@Schema(description = "스탬프 관련 필터 프로필")
	String thumbnail;
	@Schema(description = "스탬프 관련 리뷰 생성일")
	Date createdAt;
	@Schema(description = "스탬프 관련 필터 등급")
	Membership membership;
	@Schema(description = "리뷰 id")
	Long reviewId;
	@Schema(description = "스탬프 관련 필터 OS 정보")
	OS os;

	public static List<StampDto> of(List<Review> result) {
		return result.stream().map(res -> {
				Filter filter = res.getFilter();
				Photographer photographer = filter.getPhotographer();
				return StampDto.builder()
					.filterId(filter.getId())
					.filterName(filter.getName())
					.photographer(photographer.getUsername())
					.thumbnail(filter.getThumbnail())
					.membership(filter.getMembership())
					.createdAt(res.getCreatedAt())
					.reviewId(res.getId())
					.os(filter.getOs())
					.build();
			}
		).toList();
	}
}
