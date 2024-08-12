package com.example.purithm.domain.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long>, CustomFilterRepository {
	@Query("SELECT f.id, f.name, f.membership, f.photographer.username, f.thumbnail, COUNT(fl.id) AS count "
		+ "FROM FilterLike fl "
		+ "LEFT JOIN Filter f ON fl.filter.id=f.id "
		+ "WHERE fl.user.id=:userId "
		+ "GROUP BY fl.filter.id")
	List<Object[]> getLikedFilter(Long userId);
}
