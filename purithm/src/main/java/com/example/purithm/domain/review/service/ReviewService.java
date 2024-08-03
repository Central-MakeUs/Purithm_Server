package com.example.purithm.domain.review.service;

import org.springframework.stereotype.Service;

import com.example.purithm.domain.review.dto.response.ReviewResponseDto;
import com.example.purithm.domain.review.entity.Review;
import com.example.purithm.domain.review.repository.ReviewRepository;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;

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
}
