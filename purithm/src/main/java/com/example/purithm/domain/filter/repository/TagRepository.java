package com.example.purithm.domain.filter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	@Query("SELECT t.filter FROM Tag t WHERE t.tag = :tag AND t.filter.os = :os ORDER BY t.filter.createdAt ASC")
	List<Filter> findFilterByTagAndOsOrderByCreatedAtAsc(@Param("tag") String tag, @Param("os") OS os);

	@Query("SELECT t.filter FROM Tag t WHERE t.tag = :tag AND t.filter.os = :os ORDER BY t.filter.createdAt DESC")
	List<Filter> findFilterByTagAndOsOrderByCreatedAtDesc(@Param("tag") String tag, @Param("os") OS os);

	@Query("SELECT t.filter FROM Tag t WHERE t.tag = :tag AND t.filter.os = :os ORDER BY t.filter.likes DESC")
	List<Filter> findFilterByTagAndOsOrderByLikesDesc(@Param("tag") String tag, @Param("os") OS os);
}
