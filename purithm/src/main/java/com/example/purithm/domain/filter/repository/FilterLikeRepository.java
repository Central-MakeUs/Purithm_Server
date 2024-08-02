package com.example.purithm.domain.filter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purithm.domain.filter.entity.FilterLike;

public interface FilterLikeRepository extends JpaRepository<FilterLike, Long> {
	Optional<FilterLike> findByFilterIdAndUserId(Long filterId, Long userId);
	void deleteByFilterIdAndUserId(Long filterId, Long userId);
}
