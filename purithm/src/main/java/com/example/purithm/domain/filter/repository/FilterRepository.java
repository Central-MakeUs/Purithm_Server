package com.example.purithm.domain.filter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
	@Query("SELECT f FROM Filter f WHERE f.os=:os ORDER BY f.createdAt DESC")
	Page<Filter> findAllByOs(OS os, Pageable pageable);
	@Query("SELECT f, COUNT(fl.id) AS like_count " +
		"FROM Filter f " +
		"LEFT JOIN FilterLike fl ON f.id = fl.filter.id " +
		"WHERE f.os = :os " +
		"GROUP BY f.id " +
		"ORDER BY like_count DESC, f.id ASC")
	Page<Object[]> findAllWithLikeSorting(OS os, Pageable pageable);

	@Query("SELECT f, AVG(r.pureDegree) AS avg " +
		"FROM Filter f " +
		"LEFT JOIN Review r ON f.id = r.filter.id " +
		"WHERE f.os=:os " +
		"GROUP BY f.id " +
		"ORDER BY avg DESC, f.id ASC")
	Page<Object[]> findAllWithReviewSorting(OS os, Pageable pageable);

}
