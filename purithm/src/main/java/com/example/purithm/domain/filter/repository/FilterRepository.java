package com.example.purithm.domain.filter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
	Page<Filter> findAllByOs(OS os, Pageable pageable);
}
