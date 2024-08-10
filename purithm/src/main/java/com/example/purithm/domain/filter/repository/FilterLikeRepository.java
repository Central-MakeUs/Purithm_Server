package com.example.purithm.domain.filter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.FilterLike;

public interface FilterLikeRepository extends JpaRepository<FilterLike, Long> {
	Optional<FilterLike> findByFilterIdAndUserId(Long filterId, Long userId);
	void deleteByFilterIdAndUserId(Long filterId, Long userId);

	@Query("select count(fl.user) as cnt\n"
		+ "from FilterLike fl\n"
		+ "where fl.filter = :filter")
	int getLikes(@Param("filter") Filter filter);

	boolean existsByFilterIdAndUserId(Long filterId, Long userId);
}
