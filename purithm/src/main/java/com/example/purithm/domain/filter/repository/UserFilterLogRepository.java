package com.example.purithm.domain.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.UserFilterLog;
import com.example.purithm.domain.filter.repository.projection.ViewHistoryProjection;

@Repository
public interface UserFilterLogRepository extends JpaRepository<UserFilterLog, Long> {
	boolean existsByFilterIdAndUserId(Long filterId, Long userId);

	@Query("SELECT f.id AS filterId, f.name AS filterName, p.username AS photographer, f.membership AS membership, ufl.createdAt AS createdAt, r.id AS reviewId " +
		"FROM UserFilterLog ufl "
		+ "LEFT JOIN Filter f ON ufl.filterId = f.id "
		+ "LEFT JOIN Review r ON r.filter.id = f.id AND r.user.id=:userId "
		+ "LEFT JOIN Photographer  p ON p.id = f.photographer.id "
		+ "WHERE ufl.userId=:userId")
	List<ViewHistoryProjection> getFilterViewHistory(@Param("userId") Long userId);
}
