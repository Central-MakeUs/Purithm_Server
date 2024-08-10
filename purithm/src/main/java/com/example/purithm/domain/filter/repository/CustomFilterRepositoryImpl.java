package com.example.purithm.domain.filter.repository;

import static com.example.purithm.domain.filter.entity.QFilter.*;
import static com.example.purithm.domain.filter.entity.QFilterLike.*;
import static com.example.purithm.domain.review.entity.QReview.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.entity.Tag;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomFilterRepositoryImpl implements CustomFilterRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<Filter> findAllByOs(OS os, Tag tag, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(filter.os.eq(os));
		if (tag != null) {
			builder.and(filter.tag.eq(tag));
		}

		List<Filter> results = jpaQueryFactory
			.selectFrom(filter)
			.where(builder)
			.orderBy(filter.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = jpaQueryFactory
			.select(filter.count())
			.from(filter)
			.where(filter.os.eq(os)).fetchOne();

		return new PageImpl<>(results, pageable, total);
	}

	@Override
	public Page<Object[]> findAllWithLikeSorting(OS os, Tag tag, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(filter.os.eq(os));
		if (tag != null) {
			builder.and(filter.tag.eq(tag));
		}

		List<Tuple> tuples = jpaQueryFactory
			.select(filter, filterLike.count())
			.from(filter)
			.leftJoin(filterLike).on(filter.id.eq(filterLike.filter.id))
			.where(builder)
			.groupBy(filter.id)
			.orderBy(filterLike.count().desc(), filter.id.asc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.count())
			.from(filter)
			.leftJoin(filterLike).on(filter.id.eq(filterLike.filter.id))
			.where(builder)
			.fetchOne();

		List<Object[]> results = tuples.stream()
			.map(tuple -> new Object[]{tuple.get(filter), tuple.get(filterLike.count())})
			.collect(Collectors.toList());

		return new PageImpl<>(results, pageable, total);
	}

	@Override
	public Page<Object[]> findAllWithReviewSorting(OS os, Tag tag, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(filter.os.eq(os));
		if (tag != null) {
			builder.and(filter.tag.eq(tag));
		}

		List<Tuple> tuples = jpaQueryFactory
			.select(filter, review.pureDegree.avg().as("avg"))
			.from(filter)
			.leftJoin(review).on(filter.id.eq(review.filter.id))
			.where(builder)
			.groupBy(filter.id)
			.orderBy(review.pureDegree.avg().desc(), filter.id.asc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.count())
			.from(filter)
			.leftJoin(review).on(filter.id.eq(review.filter.id))
			.where(builder)
			.fetchOne();

		List<Object[]> results = tuples.stream()
			.map(tuple -> new Object[]{tuple.get(filter), tuple.get(Expressions.stringPath("avg"))})
			.collect(Collectors.toList());

		return new PageImpl<>(results, pageable, total);
	}
}
