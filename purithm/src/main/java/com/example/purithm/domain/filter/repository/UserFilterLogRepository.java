package com.example.purithm.domain.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.UserFilterLog;

@Repository
public interface UserFilterLogRepository extends JpaRepository<UserFilterLog, Long> {
	boolean existsByFilterIdAndUserId(Long filterId, Long userId);
}