package com.example.purithm.domain.filter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.purithm.domain.filter.dto.response.FilterPictureDto;
import com.example.purithm.domain.filter.dto.response.filterDetailValue.AOSFilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.filter.dto.response.FilterListDto;
import com.example.purithm.domain.filter.dto.response.FilterReviewDto;
import com.example.purithm.domain.filter.dto.response.filterDetailValue.IOSFilterDetailDto;
import com.example.purithm.domain.filter.dto.response.ReviewDto;
import com.example.purithm.domain.filter.entity.AOSFilterDetail;
import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.FilterLike;
import com.example.purithm.domain.filter.entity.IOSFilterDetail;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.repository.AOSFilterDetailRepository;
import com.example.purithm.domain.filter.repository.FilterLikeRepository;
import com.example.purithm.domain.filter.repository.IOSFilterDetailRepository;
import com.example.purithm.domain.filter.repository.FilterRepository;
import com.example.purithm.domain.filter.repository.TagRepository;
import com.example.purithm.domain.review.repository.ReviewRepository;
import com.example.purithm.domain.user.entity.User;
import com.example.purithm.domain.user.repository.UserRepository;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilterService {

	private final FilterRepository filterRepository;
	private final TagRepository tagRepository;
	private final IOSFilterDetailRepository iOSFilterDetailRepository;
	private final AOSFilterDetailRepository aOSFilterDetailRepository;
	private final UserRepository userRepository;
	private final FilterLikeRepository filterLikeRepository;
	private final ReviewRepository reviewRepository;


	public FilterListDto getFilters(Long id, int page, int size, OS os, String tag, String sortedBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending()); // 정렬 없을 때는 최신 순
		switch (sortedBy) {
			case "earliest" -> { // 오래된 순 정렬
				pageRequest = PageRequest.of(page, size, Sort.by("createdAt").ascending());
			} case "popular" -> { // 퓨어지수 높은 순
				pageRequest = PageRequest.of(page, size, Sort.by("likes").descending());
			}
		}

		User user = userRepository.findById(id)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		if (tag == null) {
			Page<Filter> filters = filterRepository.findAllByOs(os, pageRequest);
			return FilterListDto.builder()
				.isLast(filters.isLast())
				.filters(
					filters.getContent().stream().map(filter ->
						FilterDto.of(filter, user.getMembership(), isLike(filter.getId(), id))).toList())
				.build();
		}

		Page<Filter> filters = tagRepository.findFilterByTagAndOs(tag, os, pageRequest);
		return FilterListDto.builder()
			.isLast(filters.isLast())
			.filters(
				filters.getContent().stream().map(filter ->
					FilterDto.of(filter, user.getMembership(), isLike(filter.getId(), id))).toList())
			.build();
	}

	private boolean isLike(Long filterId, Long userId) {
		return filterLikeRepository.findByFilterIdAndUserId(filterId, userId).isPresent();
	}

	public FilterDetailDto getFilterDetail(Long id, Long filterId) {
		Filter filter = filterRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		List<String> pictures = filter.getPictures();
		List<String> originalPictures = filter.getOriginalPictures();
		List<FilterPictureDto> filters = new ArrayList<>();

		for (int i=0; i< pictures.size(); i++) {
			filters.add(FilterPictureDto.builder()
				.picture(pictures.get(i))
				.originalPicture(originalPictures.get(i)).build());
		}

		return FilterDetailDto.builder()
			.name(filter.getName())
			.likes(filter.getLikes())
			.pureDegree(filter.getPureDegree())
			.pictures(filters)
			.liked(isLike(filterId, id))
			.build();
	}

	public AOSFilterDetailDto getFilterAOSDetail(Long filterId) {
		AOSFilterDetail aosFilterDetail = aOSFilterDetailRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		return AOSFilterDetailDto.of(aosFilterDetail);
	}

	public IOSFilterDetailDto getFilterIOSDetail(Long filterId) {
		IOSFilterDetail iosFilterDetail = iOSFilterDetailRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		return IOSFilterDetailDto.of(iosFilterDetail);
	}

	public void likeFilter(Long userId, Long filterId) {
		Filter filter = filterRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		User user = userRepository.findById(userId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		FilterLike like = FilterLike.builder()
			.filter(filter).user(user).build();

		filterLikeRepository.save(like);
	}

	public void dislikeFilter(Long userId, Long filterId) {
		filterLikeRepository.deleteByFilterIdAndUserId(filterId, userId);
	}

	@Transactional
	public FilterReviewDto getReviews(Long filterId) {
		Integer avg = reviewRepository.getAverage(filterId);
		List<ReviewDto> reviews = reviewRepository.findAllByFilterId(filterId)
			.stream().map(ReviewDto::of).toList();

		return FilterReviewDto.of(Optional.ofNullable(avg).orElse(0), reviews);
	}
}
