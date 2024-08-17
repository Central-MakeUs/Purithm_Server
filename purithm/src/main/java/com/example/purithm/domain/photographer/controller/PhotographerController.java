package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.filter.dto.response.FilterListDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.service.FilterService;
import com.example.purithm.domain.photographer.service.PhotographerService;
import com.example.purithm.domain.photographer.dto.response.PhotographerDto;
import com.example.purithm.global.response.SuccessResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/photographers")
@RequiredArgsConstructor
public class PhotographerController implements PhotographerControllerDocs {

  private final PhotographerService photographerService;
  private final FilterService filterService;

  @GetMapping
  public SuccessResponse<List<PhotographerDto>> getPhotographers(Long id, String sortedBy) {
    return SuccessResponse.of(photographerService.getPhotographerList(sortedBy));
  }

  @GetMapping("/{photographerId}")
  public SuccessResponse<PhotographerDto> getPhotographerInfo(Long id, Long photographerId) {
    return SuccessResponse.of(photographerService.getPhotographer(photographerId));
  }

  @GetMapping("/{photographerId}/filters")
  public SuccessResponse<FilterListDto> getFiltersByPhotographer(
      Long id, Long photographerId, String sortedBy, OS os, int page, int size) {
    return SuccessResponse.of(filterService.getFilters(id, page, size, os, null, sortedBy, photographerId));
  }
}
