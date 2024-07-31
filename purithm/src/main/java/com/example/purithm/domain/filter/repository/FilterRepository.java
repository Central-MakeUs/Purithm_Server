package com.example.purithm.domain.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
	List<Filter> findAllByOsOrderByCreatedAtAsc(OS os);
	List<Filter> findAllByOsOrderByCreatedAtDesc(OS os);
	List<Filter> findAllByOsOrderByLikesDesc(OS os);
}
