package com.example.purithm.domain.filter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.entity.Tag;

public interface CustomFilterRepository {
	Page<Filter> findAllByOs(OS os, Tag tag, Long photographerId, Pageable pageable);
	Page<Object[]> findAllWithLikeSorting(OS os, Tag tag, Long photographerId, Pageable pageable);
	Page<Object[]> findAllWithReviewSorting(OS os, Tag tag, Long photographerId, Pageable pageable);
	Page<Object[]> findAllWithViewsSorting(OS os, Long photographerId, Pageable pageable);
	Page<Filter> findAllWithNameSorting(OS os, Tag tag, Long photographerId, Pageable pageable);
	Page<Filter> findAllWithMembershipSorting(OS os, Tag tag, Long photographerId, Pageable pageable);
}
