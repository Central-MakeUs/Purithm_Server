package com.example.purithm.domain.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.repository.projection.FilterLikeProjection;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long>, CustomFilterRepository {
	@Query("SELECT f.id AS filterId, f.name AS filterName, f.membership AS membership, f.photographer.username AS photographer, f.thumbnail AS thumbnail, COUNT(fl.id) AS count, f.os AS os "
		+ "FROM FilterLike fl "
		+ "LEFT JOIN Filter f ON fl.filter.id=f.id "
		+ "WHERE fl.user.id=:userId "
		+ "GROUP BY fl.filter.id")
	List<FilterLikeProjection> getLikedFilter(Long userId);
}
