package com.example.purithm.domain.filter.repository.projection;

import java.util.Date;

import com.example.purithm.domain.filter.entity.Membership;

public interface ViewHistoryProjection {
	Long getFilterId();
	String getFilterName();
	String getPhotographer();
	Membership getMembership();
	Date getCreatedAt();
	Long getReviewId();
}
