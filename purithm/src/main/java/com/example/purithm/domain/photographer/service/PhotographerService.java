package com.example.purithm.domain.photographer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.purithm.domain.photographer.dto.response.PhotographerDto;
import com.example.purithm.domain.photographer.entity.Photographer;
import com.example.purithm.domain.photographer.repository.PhotographerRepository;
import com.example.purithm.global.exception.CustomException;
import com.example.purithm.global.exception.Error;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotographerService {

	private final PhotographerRepository photographerRepository;

	public List<PhotographerDto> getPhotographerList(String sortedBy) {
		List<Photographer> photographers;
		if (sortedBy.equals("latest")) {
			photographers = photographerRepository.findAllOrderedByCreatedAtDesc();
		} else if (sortedBy.equals("earliest")) {
			photographers = photographerRepository.findAllOrderedByCreatedAtAsc();
		} else {
			photographers = photographerRepository.findAllOrderedByCount();
		}

		return photographers.stream().map(photographer ->
			PhotographerDto.builder()
				.id(photographer.getId())
				.name(photographer.getUsername())
				.profile(photographer.getProfile())
				.description(photographer.getDescription())
				.picture(photographer.getThumbnails())
				.createdAt(photographer.getCreatedAt()).build())
			.toList();
	}

	public PhotographerDto getPhotographer(Long id) {
		Photographer photographer = photographerRepository.findById(id)
			.orElseThrow(() -> CustomException.of(Error.NOT_FOUND_ERROR));

		return PhotographerDto.builder()
			.id(photographer.getId())
			.name(photographer.getUsername())
			.profile(photographer.getProfile())
			.description(photographer.getDescription())
			.picture(photographer.getThumbnails())
			.createdAt(photographer.getCreatedAt())
			.build();
	}
}
