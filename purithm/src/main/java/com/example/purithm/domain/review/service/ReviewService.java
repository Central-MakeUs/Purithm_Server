package com.example.purithm.domain.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.purithm.domain.feed.dto.response.FeedDto;
import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.repository.FilterRepository;
import com.example.purithm.domain.review.dto.request.ReviewRequestDto;
import com.example.purithm.domain.review.dto.response.CreatedReviewDto;
import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.domain.review.entity.Review;
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
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final FilterRepository filterRepository;

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
			.pictures(request.picture())
			.pureDegree(request.pureDegree())
			.build();
		Review savedReview = reviewRepository.save(review);

		upgradeMembership(user);

		return CreatedReviewDto.builder()
			.id(savedReview.getId()).build();
	}

	private void upgradeMembership(User user) {
		int numOfReviews = reviewRepository.countAllByUser(user);
		if (numOfReviews == 16) {
			user.upgradeToPremiumPlus();
			userRepository.save(user);
		} else if (numOfReviews == 8) {
			user.upgradeToPremium();
			userRepository.save(user);
		}
	}

	public List<FeedDto> getFeeds(OS os, String sortedBy) {
		List<Review> reviews;
		if (sortedBy.equals("earliest")) {
			reviews = reviewRepository.findAllOrderByCreatedAtAsc(os);
		} else if (sortedBy.equals("pure")) {
			reviews = reviewRepository.findAllOrderByPureDegree(os)
				.stream().map(result -> (Review) result[0])
				.toList();
		} else {
			reviews = reviewRepository.findAllOrderByCreatedAtDesc(os);
		}

		return reviews.stream().map(review ->
			FeedDto.builder()
				.filterId(review.getFilter().getId())
				.filterName(review.getFilter().getName())
				.writer(review.getUser().getUsername())
				.profile(review.getUser().getProfile())
				.pureDegree(review.getPureDegree())
				.content(review.getContent())
				.createdAt(review.getCreatedAt())
				.pictures(review.getPictures()).build()).toList();
	}

	public List<FeedDto> getMyReviews(Long userId) {
		List<Review> reviews = reviewRepository.findAllByUserId(userId);
		return reviews.stream().map(review ->
			FeedDto.builder()
				.filterId(review.getFilter().getId())
				.filterName(review.getFilter().getName())
				.writer(review.getUser().getUsername())
				.profile(review.getUser().getProfile())
				.pureDegree(review.getPureDegree())
				.content(review.getContent())
				.createdAt(review.getCreatedAt())
				.pictures(review.getPictures()).build()).toList();
	}

	@Transactional
	public void deleteReview(Long userId, Long reviewId) {
		reviewRepository.deleteByIdAndUserId(reviewId, userId);
	}
}
