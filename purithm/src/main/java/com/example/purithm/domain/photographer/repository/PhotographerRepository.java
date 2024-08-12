package com.example.purithm.domain.photographer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.photographer.entity.Photographer;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {
	@Query("SELECT p FROM Photographer p ORDER BY p.createdAt DESC")
	List<Photographer> findAllOrderedByCreatedAtDesc();

	@Query("SELECT p FROM Photographer p ORDER BY p.createdAt ASC")
	List<Photographer> findAllOrderedByCreatedAtAsc();

	@Query("SELECT p, count(f.photographer) AS count "
		+ "FROM Filter f LEFT JOIN Photographer p ON p.id=f.photographer.id "
		+ "GROUP BY p "
		+ "ORDER BY count DESC")
	List<Photographer> findAllOrderedByCount();
}
