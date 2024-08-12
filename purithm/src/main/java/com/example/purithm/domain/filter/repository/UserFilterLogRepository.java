package com.example.purithm.domain.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.UserFilterLog;

@Repository
public interface UserFilterLogRepository extends JpaRepository<UserFilterLog, Long> {
	boolean existsByFilterIdAndUserId(Long filterId, Long userId);

	@Query("SELECT f.id, f.name, p.username, f.membership, ufl.createdAt, r.id " +
		"FROM UserFilterLog ufl "
		+ "LEFT JOIN Filter f ON ufl.filterId = f.id "
		+ "LEFT JOIN Review r ON r.filter.id = f.id AND r.user.id=:userId"
		+ "LEFT JOIN Photographer  p ON p.id = f.photographer.id "
		+ "WHERE ufl.userId=:userId")
	List<Object[]> getFilterViewHistory(Long userId);
}
