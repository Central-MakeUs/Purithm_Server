package com.example.purithm.domain.filter.repository;

import static com.example.purithm.domain.filter.entity.QFilter.*;
import static com.example.purithm.domain.filter.entity.QFilterLike.*;
import static com.example.purithm.domain.filter.entity.QUserFilterLog.*;
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
	public Page<Filter> findAllByOs(OS os, Tag tag, Long photographerId, Pageable pageable) {
		BooleanBuilder builder = this.createBuilder(os, tag, photographerId);

		List<Filter> results = jpaQueryFactory
			.selectFrom(filter)
			.where(builder)
			.orderBy(filter.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.count())
			.from(filter)
			.where(builder).fetchOne();

		return new PageImpl<>(results, pageable, total);
	}

	@Override
	public Page<Object[]> findAllWithLikeSorting(OS os, Tag tag, Long photographerId, Pageable pageable) {
		BooleanBuilder builder = this.createBuilder(os, tag, photographerId);

		List<Tuple> tuples = jpaQueryFactory
			.select(filter, filterLike.count())
			.from(filter)
			.leftJoin(filterLike).on(filter.id.eq(filterLike.filter.id))
			.where(builder)
			.groupBy(filter.id)
			.orderBy(filterLike.count().desc(), filter.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.countDistinct())
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
	public Page<Object[]> findAllWithReviewSorting(OS os, Tag tag, Long photographerId, Pageable pageable) {
		BooleanBuilder builder = this.createBuilder(os, tag, photographerId);

		List<Tuple> tuples = jpaQueryFactory
			.select(filter, review.pureDegree.avg().as("avg"))
			.from(filter)
			.leftJoin(review).on(filter.id.eq(review.filter.id))
			.where(builder)
			.groupBy(filter.id)
			.orderBy(review.pureDegree.avg().desc(), filter.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.countDistinct())
			.from(filter)
			.leftJoin(review).on(filter.id.eq(review.filter.id))
			.where(builder)
			.fetchOne();

		List<Object[]> results = tuples.stream()
			.map(tuple -> new Object[]{tuple.get(filter), tuple.get(Expressions.stringPath("avg"))})
			.collect(Collectors.toList());

		return new PageImpl<>(results, pageable, total);
	}

	@Override
	public Page<Object[]> findAllWithViewsSorting(OS os, Long photographerId, Pageable pageable) {
		BooleanBuilder builder = this.createBuilder(os, null, photographerId);

		List<Tuple> tuples = jpaQueryFactory
			.select(filter, userFilterLog.filterId.count().as("count"))
			.from(filter)
			.leftJoin(userFilterLog).on(filter.id.eq(userFilterLog.filterId))
			.where(builder)
			.groupBy(filter.id)
			.orderBy(userFilterLog.filterId.count().desc(), filter.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.countDistinct())
			.from(filter)
			.leftJoin(review).on(filter.id.eq(review.filter.id))
			.where(builder)
			.fetchOne();

		List<Object[]> results = tuples.stream()
			.map(tuple -> new Object[]{tuple.get(filter), tuple.get(Expressions.stringPath("count"))})
			.collect(Collectors.toList());

		return new PageImpl<>(results, pageable, total);
	}

	@Override
	public Page<Filter> findAllWithNameSorting(OS os, Tag tag, Long photographerId, Pageable pageable) {
		BooleanBuilder builder = this.createBuilder(os, tag, photographerId);

		List<Filter> results = jpaQueryFactory
			.selectFrom(filter)
			.where(builder)
			.orderBy(filter.name.asc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.count())
			.from(filter)
			.where(builder).fetchOne();

		return new PageImpl<>(results, pageable, total);
	}

	@Override
	public Page<Filter> findAllWithMembershipSorting(OS os, Tag tag, Long photographerId, Pageable pageable) {
		BooleanBuilder builder = this.createBuilder(os, tag, photographerId);

		List<Filter> results = jpaQueryFactory
			.selectFrom(filter)
			.where(builder)
			.orderBy(filter.membership.asc(), filter.name.asc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = jpaQueryFactory
			.select(filter.count())
			.from(filter)
			.where(builder).fetchOne();

		return new PageImpl<>(results, pageable, total);
	}

	private BooleanBuilder createBuilder(OS os, Tag tag, Long photographerId) {
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(filter.os.eq(os));
		if (tag != null) {
			builder.and(filter.tag.eq(tag));
		}
		if (photographerId != null) {
			builder.and(filter.photographer.id.eq(photographerId));
		}
		return builder;
	}

}
