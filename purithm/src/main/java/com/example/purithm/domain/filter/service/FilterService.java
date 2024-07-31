package com.example.purithm.domain.filter.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.purithm.domain.filter.dto.response.FilterDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.repository.FilterRepository;
import com.example.purithm.domain.filter.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterService {

	private final FilterRepository filterRepository;
	private final TagRepository tagRepository;

	public List<FilterDto> getFilters(int page, int size, OS os, String tag, String sortedBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending()); // 정렬 없을 때는 최신 순
		switch (sortedBy) {
			case "earliest" -> { // 오래된 순 정렬
				pageRequest = PageRequest.of(page, size, Sort.by("createdAt").ascending());
			} case "popular" -> { // 퓨어지수 높은 순
				pageRequest = PageRequest.of(page, size, Sort.by("likes").descending());
			}
		}

		if (tag == null) {
			return filterRepository.findAllByOs(os, pageRequest)
				.stream().map(FilterDto::of).toList();
		}
		return tagRepository.findFilterByTagAndOs(tag, os, pageRequest).stream().map(FilterDto::of).toList();
	}
}
