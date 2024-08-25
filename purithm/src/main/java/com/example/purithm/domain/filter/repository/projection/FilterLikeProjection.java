package com.example.purithm.domain.filter.repository.projection;

import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.filter.entity.OS;

public interface FilterLikeProjection {
	Long getFilterId();
	String getFilterName();
	Membership getMembership();
	String getPhotographer();
	String getThumbnail();
	Long getCount();
	OS getOs();
}
