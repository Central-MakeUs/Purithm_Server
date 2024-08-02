package com.example.purithm.domain.filter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.FilterLike;
import com.example.purithm.domain.user.entity.User;

public interface FilterLikeRepository extends JpaRepository<FilterLike, Long> {
	Optional<FilterLike> findByFilterAndUser(Filter filter, User user);
}
