package com.example.purithm.domain.filter.repository.projection;

import java.util.Date;

import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;

public interface ViewHistoryProjection {
	Long getFilterId();
	String getFilterName();
	String getThumbnail();
	String getPhotographer();
	Membership getMembership();
	Date getCreatedAt();
	Long getReviewId();
	OS getOs();
}
