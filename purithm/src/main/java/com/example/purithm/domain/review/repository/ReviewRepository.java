package com.example.purithm.domain.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.purithm.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findAllByFilterId(Long filterId);
	@Query("select avg(r.pureDegree) as avg\n"
		+ "from Review r\n"
		+ "where r.filter.id = :id")
	int getAverage(@Param("id") Long filterId);
}
