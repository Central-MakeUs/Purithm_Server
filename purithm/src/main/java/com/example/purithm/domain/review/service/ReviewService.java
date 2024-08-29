package com.example.purithm.domain.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.domain.filter.dto.response.DatedStampDto;
import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.repository.FilterRepository;
import com.example.purithm.domain.review.dto.request.ReviewRequestDto;
import com.example.purithm.domain.review.dto.response.CreatedReviewDto;
import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.domain.review.entity.Review;
import com.example.purithm.domain.review.repository.ReviewRepository;
import com.example.purithm.domain.user.entity.BlockedUser;
import com.example.purithm.domain.user.entity.User;
import com.example.purithm.domain.user.repository.BlockedUserRepository;
import com.example.purithm.domain.user.repository.UserRepository;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final FilterRepository filterRepository;
	private final BlockedUserRepository blockedUserRepository;

	public ReviewResponseDto getReview(Long id) {
		Review review = reviewRepository.findById(id)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		return ReviewResponseDto.builder()
			.content(review.getContent())
			.username(review.getUser().getUsername())
			.userProfile(review.getUser().getProfile())
			.createdAt(review.getCreatedAt())
			.pictures(review.getPictures())
			.pureDegree(review.getPureDegree())
			.build();
	}

	@Transactional
	public CreatedReviewDto writeReview(Long id, ReviewRequestDto request) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		Filter filter = filterRepository.findById(request.filterId())
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		Review review = Review.builder()
			.user(user)
			.filter(filter)
			.content(request.content())
			.pictures(request.pictures())
			.pureDegree(request.pureDegree())
			.build();
		Review savedReview = reviewRepository.save(review);

		upgradeMembership(user);

		return CreatedReviewDto.builder()
			.id(savedReview.getId()).build();
	}

	private void upgradeMembership(User user) {
		int numOfReviews = reviewRepository.countAllByUser(user);
		user.updateMembership(numOfReviews);
		userRepository.save(user);
	}

	public List<FeedDto> getFeeds(OS os, String sortedBy, Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		List<Review> reviews;
		if (sortedBy.equals("earliest")) {
			reviews = reviewRepository.findAllOrderByCreatedAtAsc(os, userId);
		} else if (sortedBy.equals("pure")) {
			reviews = reviewRepository.findAllOrderByPureDegree(os, userId)
				.stream().map(result -> (Review) result[0])
				.toList();
		} else {
			reviews = reviewRepository.findAllOrderByCreatedAtDesc(os, userId);
		}

		return changeReviewToFeedDto(reviews, user.getMembership());
	}

	public List<FeedDto> getMyReviews(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		List<Review> reviews = reviewRepository.findAllByUserId(userId);
		return changeReviewToFeedDto(reviews, user.getMembership());
	}

	@Transactional
	public void deleteReview(Long userId, Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		reviewRepository.deleteByIdAndUserId(reviewId, userId);
	}

	public DatedStampDto getStamps(Long userId) {
		List<Review> reviews = reviewRepository.findAllByUserId(userId);
		return DatedStampDto.of(reviews);
	}

	public void blockUser(Long userId, Long feedId) {
		Review review = reviewRepository.findById(feedId)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		User user = review.getUser();

		if (!blockedUserRepository.existsByUserIdAndBlockedUserId(userId, user.getId())) {
			BlockedUser blockedUser = BlockedUser.builder()
				.blockedUserId(user.getId())
				.userId(userId)
				.build();

			blockedUserRepository.save(blockedUser);
		}
	}

	private List<FeedDto> changeReviewToFeedDto(List<Review> reviews, Membership membership) {
		return reviews.stream().map(review ->
			FeedDto.builder()
				.filterId(review.getFilter().getId())
				.filterName(review.getFilter().getName())
				.writer(review.getUser().getUsername())
				.profile(review.getUser().getProfile())
				.pureDegree(review.getPureDegree())
				.content(review.getContent())
				.createdAt(review.getCreatedAt())
				.pictures(review.getPictures())
				.id(review.getId())
				.os(review.getFilter().getOs())
				.filterThumbnail(review.getFilter().getThumbnail())
				.canAccess(checkAccess(membership, review.getFilter().getMembership())).build()).toList();
	}

	private static boolean checkAccess(Membership membership, Membership filter) {
		return filter.compareTo(membership) > 0 ? false : true;
	}
}
