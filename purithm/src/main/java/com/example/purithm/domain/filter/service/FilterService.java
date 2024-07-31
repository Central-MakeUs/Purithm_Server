package com.example.purithm.domain.filter.service;

import java.util.List;

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

	public List<FilterDto> getFilters(OS os, String tag, String sortedBy) {
		switch (sortedBy) {
			case "earliest" -> { // 오래된 순 정렬
				if (tag == null) return filterRepository.findAllByOsOrderByCreatedAtAsc(os).stream().map(FilterDto::of).toList();
				return tagRepository.findFilterByTagAndOsOrderByCreatedAtAsc(tag, os).stream().map(FilterDto::of).toList();
			} case "popular" -> { // 퓨어지수 높은 순
				if (tag == null) return filterRepository.findAllByOsOrderByLikesDesc(os).stream().map(FilterDto::of).toList();
				return tagRepository.findFilterByTagAndOsOrderByLikesDesc(tag, os).stream().map(FilterDto::of).toList();
			} default -> { // 정렬 없을 때는 최신 순
				if (tag == null) return filterRepository.findAllByOsOrderByCreatedAtDesc(os).stream().map(FilterDto::of).toList();
				return tagRepository.findFilterByTagAndOsOrderByCreatedAtDesc(tag, os).stream().map(FilterDto::of).toList();
			}
		}
	}
}
