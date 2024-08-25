package com.example.purithm.domain.filter.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.purithm.domain.filter.dto.response.DatedFilterDto;
import com.example.purithm.domain.filter.dto.response.FilterDescriptionDto;
import com.example.purithm.domain.filter.dto.response.FilterPictureDto;
import com.example.purithm.domain.filter.dto.response.LikedFilterDto;
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
import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.entity.Tag;
import com.example.purithm.domain.filter.entity.UserFilterLog;
import com.example.purithm.domain.filter.repository.AOSFilterDetailRepository;
import com.example.purithm.domain.filter.repository.FilterLikeRepository;
import com.example.purithm.domain.filter.repository.IOSFilterDetailRepository;
import com.example.purithm.domain.filter.repository.FilterRepository;
import com.example.purithm.domain.filter.repository.UserFilterLogRepository;
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
	private final IOSFilterDetailRepository iOSFilterDetailRepository;
	private final AOSFilterDetailRepository aOSFilterDetailRepository;
	private final UserRepository userRepository;
	private final FilterLikeRepository filterLikeRepository;
	private final ReviewRepository reviewRepository;
	private final UserFilterLogRepository userFilterLogRepository;


	public FilterListDto getFilters(Long id, int page, int size, OS os, Tag tag, String sortedBy, Long photographerId) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Object[]> filters;
		boolean isLast;
		int totalPage;
		long totalElement;
		List<FilterDto> filterDtos;

		if (sortedBy.equals("popular")) {
			filters = filterRepository.findAllWithLikeSorting(os, tag, photographerId, pageRequest);
			isLast = filters.isLast();
			totalPage = filters.getTotalPages();
			totalElement = filters.getTotalElements();
			filterDtos = filters.getContent().stream().map(filter ->
				FilterDto.of(
					(Filter) filter[0],
					isLike(((Filter) filter[0]).getId(), id),
					filterLikeRepository.getLikes((Filter) filter[0]),
					checkAccess(user.getMembership(), ((Filter)filter[0]).getMembership()))).toList();
		} else if (sortedBy.equals("pure")) {
			filters = filterRepository.findAllWithReviewSorting(os, tag, photographerId, pageRequest);
			isLast = filters.isLast();
			totalPage = filters.getTotalPages();
			totalElement = filters.getTotalElements();
			filterDtos = filters.getContent().stream().map(filter ->
				FilterDto.of(
					(Filter) filter[0],
					isLike(((Filter) filter[0]).getId(), id),
					filterLikeRepository.getLikes((Filter) filter[0]),
					checkAccess(user.getMembership(), ((Filter)filter[0]).getMembership()))).toList();
		} else if (sortedBy.equals("earliest")) {
			pageRequest = PageRequest.of(page, size, Sort.by("createdAt").ascending()); // 정렬 없을 때는 최신 순
			Page<Filter> filterByEarliest = filterRepository.findAllByOs(os, tag, photographerId, pageRequest);
			isLast = filterByEarliest.isLast();
			totalPage = filterByEarliest.getTotalPages();
			totalElement = filterByEarliest.getTotalElements();
			filterDtos = filterByEarliest.getContent().stream().map(filter ->
				FilterDto.of(
					filter,
					isLike((filter).getId(), id),
					filterLikeRepository.getLikes(filter),
					checkAccess(user.getMembership(), (filter).getMembership()))).toList();
		} else if (sortedBy.equals("views")) {
			filters = filterRepository.findAllWithViewsSorting(os, photographerId, pageRequest);
			isLast = filters.isLast();
			totalPage = filters.getTotalPages();
			totalElement = filters.getTotalElements();
			filterDtos = filters.getContent().stream().map(filter ->
				FilterDto.of(
					(Filter) filter[0],
					isLike(((Filter) filter[0]).getId(), id),
					filterLikeRepository.getLikes((Filter) filter[0]),
					checkAccess(user.getMembership(), ((Filter)filter[0]).getMembership()))).toList();
		} else {
			pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending()); // 정렬 없을 때는 최신 순
			Page<Filter> filterByLatest = filterRepository.findAllByOs(os, tag, photographerId, pageRequest);

			isLast = filterByLatest.isLast();
			totalPage = filterByLatest.getTotalPages();
			totalElement = filterByLatest.getTotalElements();
			filterDtos = filterByLatest.getContent().stream().map(filter ->
				FilterDto.of(
					filter,
					isLike((filter).getId(), id),
					filterLikeRepository.getLikes(filter),
					checkAccess(user.getMembership(), filter.getMembership()))).toList();
		}

		return FilterListDto.builder()
			.isLast(isLast)
			.totalPage(totalPage)
			.totalElement(totalElement)
			.filters(filterDtos).build();
	}

	private boolean isLike(Long filterId, Long userId) {
		return filterLikeRepository.findByFilterIdAndUserId(filterId, userId).isPresent();
	}

	public FilterDetailDto getFilterDetail(Long id, Long filterId) {
		Filter filter = filterRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		List<FilterPictureDto> filters = filter.getFilterDetails().stream().map(filterDetail ->
			FilterPictureDto.builder()
				.picture(filterDetail.getPicture())
				.originalPicture(filterDetail.getOriginalPicture())
				.build()).toList();

		return FilterDetailDto.builder()
			.name(filter.getName())
			.likes(filterLikeRepository.getLikes(filter))
			.pureDegree(Optional.ofNullable(reviewRepository.getAverage(filterId)).orElse(0))
			.pictures(filters)
			.liked(isLike(filterId, id))
			.build();
	}

	@Transactional
	public AOSFilterDetailDto getFilterAOSDetail(Long filterId, Long userId) {
		Filter filter = filterRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		AOSFilterDetail aosFilterDetail = aOSFilterDetailRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		saveUserFilterLog(filterId, userId);

		return AOSFilterDetailDto.of(filter, isLike(filterId, userId), aosFilterDetail);
	}

	@Transactional
	public IOSFilterDetailDto getFilterIOSDetail(Long filterId, Long userId) {
		Filter filter = filterRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		IOSFilterDetail iosFilterDetail = iOSFilterDetailRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		saveUserFilterLog(filterId, userId);

		return IOSFilterDetailDto.of(filter, isLike(filterId, userId), iosFilterDetail);
	}

	private void saveUserFilterLog(Long filterId, Long userId) {
		if (!userFilterLogRepository.existsByFilterIdAndUserId(filterId, userId)) {
			UserFilterLog log = UserFilterLog.builder()
				.userId(userId).filterId(filterId).build();
			userFilterLogRepository.save(log);
		}
	}

	public void likeFilter(Long userId, Long filterId) {
		if (!filterLikeRepository.existsByFilterIdAndUserId(filterId, userId)) {
			Filter filter = filterRepository.findById(filterId)
				.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
			User user = userRepository.findById(userId)
				.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

			FilterLike like = FilterLike.builder()
				.filter(filter).user(user).build();

			filterLikeRepository.save(like);
		}
	}

	@Transactional
	public void dislikeFilter(Long userId, Long filterId) {
		filterLikeRepository.deleteByFilterIdAndUserId(filterId, userId);
	}

	@Transactional
	public FilterReviewDto getReviews(Long userId, Long filterId) {
		Integer avg = reviewRepository.getAverage(filterId);
		AtomicBoolean hasReview = new AtomicBoolean(false);
		AtomicReference<Long> id = new AtomicReference<>(null);

		List<ReviewDto> reviews = reviewRepository.findAllByFilterId(filterId, userId)
			.stream().map(review -> {
				if (review.getUser().getId() == userId) {
					hasReview.set(true);
					id.set(review.getId());;
				}
				return ReviewDto.of(review);
			}).toList();

		boolean hasViewed = userFilterLogRepository.existsByFilterIdAndUserId(filterId, userId);

		return FilterReviewDto.builder()
			.avg(Optional.ofNullable(avg).orElse(0))
			.hasReview(hasReview.get())
			.reviewId(id.get())
			.hasViewed(hasViewed)
			.reviews(reviews)
			.build();
	}

	public FilterDescriptionDto getFilterDescriptions(Long filterId) {
		Filter filter = filterRepository.findById(filterId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		return FilterDescriptionDto.of(filter);
	}

	public DatedFilterDto getFilterViewHistory(Long userId) {
		return DatedFilterDto.of(userFilterLogRepository.getFilterViewHistory(userId));
	}

	public List<LikedFilterDto> getLikedFilters(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		return filterRepository.getLikedFilter(userId)
			.stream().map(result ->
				LikedFilterDto.builder()
					.id(result.getFilterId())
					.name(result.getFilterName())
					.membership(result.getMembership())
					.photographerName(result.getPhotographer())
					.thumbnail(result.getThumbnail())
					.likes(result.getCount())
					.canAccess(checkAccess(user.getMembership(), result.getMembership()))
					.os(result.getOs()).build()).toList();
	}

	private static boolean checkAccess(Membership membership, Membership filter) {
		//return filter.compareTo(membership) > 0 ? false : true;
		return true;
	}
}
