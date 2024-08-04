package com.example.purithm.domain.review.service;

import org.springframework.stereotype.Service;

import com.example.purithm.domain.filter.entity.Filter;
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
			.content(review.getReview())
			.username(review.getUser().getUsername())
			.userProfile(review.getUser().getProfile())
			.createdAt(review.getCreatedAt())
			.pictures(review.getPictures())
			.pureDegree(review.getPureDegree())
			.build();
	}

	public CreatedReviewDto writeReview(Long id, ReviewRequestDto request) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));
		Filter filter = filterRepository.findById(request.filterId())
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		Review review = Review.builder()
			.user(user)
			.filter(filter)
			.review(request.content())
			.pictures(request.picture())
			.pureDegree(request.pureDegree())
			.build();
		Review savedReview = reviewRepository.save(review);

		return CreatedReviewDto.builder()
			.id(savedReview.getId()).build();
	}
}
