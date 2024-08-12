package com.example.purithm.domain.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.review.entity.Review;
import com.example.purithm.domain.user.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findAllByFilterId(Long filterId);
	@Query("SELECT avg(r.pureDegree) as avg\n"
		+ " FROM Review r\n"
		+ " WHERE r.filter.id = :id")
	Integer getAverage(@Param("id") Long filterId);

	@Query("SELECT r FROM Review r WHERE r.filter.os=:os ORDER BY r.createdAt DESC ")
	List<Review> findAllOrderByCreatedAtDesc(OS os);

	@Query("SELECT r FROM Review r WHERE r.filter.os=:os ORDER BY r.createdAt ASC ")
	List<Review> findAllOrderByCreatedAtAsc(OS os);

	@Query("SELECT r, AVG(r.pureDegree) AS avg " +
		"FROM Review r LEFT JOIN Filter f ON r.filter.id = f.id " +
		"WHERE f.os=:os "+
		"GROUP BY r.id " +
		"ORDER BY avg DESC")
	List<Object[]> findAllOrderByPureDegree(OS os);

	int countAllByUser(User user);

	List<Review> findAllByUserId(Long userId);
}
