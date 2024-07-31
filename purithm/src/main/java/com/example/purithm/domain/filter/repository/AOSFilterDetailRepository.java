package com.example.purithm.domain.filter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.AOSFilterDetail;

@Repository
public interface AOSFilterDetailRepository extends JpaRepository<AOSFilterDetail, Long> {
}
