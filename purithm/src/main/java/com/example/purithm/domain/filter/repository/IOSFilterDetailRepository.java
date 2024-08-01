package com.example.purithm.domain.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.IOSFilterDetail;

@Repository
public interface IOSFilterDetailRepository extends JpaRepository<IOSFilterDetail, Long> {
}
