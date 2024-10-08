package com.example.purithm.domain.filter.controller;

import com.example.purithm.domain.filter.dto.response.FilterDescriptionDto;
import com.example.purithm.domain.filter.dto.response.filterDetailValue.AOSFilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterDetailDto;
import com.example.purithm.domain.filter.dto.response.FilterListDto;
import com.example.purithm.domain.filter.dto.response.FilterReviewDto;
import com.example.purithm.domain.filter.dto.response.filterDetailValue.IOSFilterDetailDto;
import com.example.purithm.domain.filter.entity.OS;
import com.example.purithm.domain.filter.entity.Tag;
import com.example.purithm.domain.filter.service.FilterService;
import com.example.purithm.global.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filters")
@RequiredArgsConstructor
public class FilterController implements FilterControllerDocs {

  private final FilterService filterService;

  @GetMapping
  public SuccessResponse<FilterListDto> getFilters(Long id, OS os, Tag tag, String sortedBy, int page, int size
  ) {
    return SuccessResponse.of(filterService.getFilters(id, page, size, os, tag, sortedBy, null));
  }

  @GetMapping("/{filterId}")
  public SuccessResponse<FilterDetailDto> getFilterDetail(Long id, Long filterId) {
    return SuccessResponse.of(filterService.getFilterDetail(id, filterId));
  }

  @GetMapping("/{filterId}/AOS")
  public SuccessResponse<AOSFilterDetailDto> getAOSFilter(Long id, Long filterId) {
    return SuccessResponse.of(filterService.getFilterAOSDetail(filterId, id));
  }

  @GetMapping("/{filterId}/iOS")
  public SuccessResponse<IOSFilterDetailDto> getIOSFilter(Long id, Long filterId) {
    return SuccessResponse.of(filterService.getFilterIOSDetail(filterId, id));
  }

  @GetMapping("/{filterId}/reviews")
  public SuccessResponse<FilterReviewDto> getReviews(Long id, Long filterId) {
    return SuccessResponse.of(filterService.getReviews(id, filterId));
  }

  @PostMapping("/{filterId}/likes")
  public SuccessResponse<Boolean> likes(Long id, Long filterId) {
    filterService.likeFilter(id, filterId);
    return SuccessResponse.of();
  }

  @DeleteMapping("/{filterId}/likes")
  public SuccessResponse<Boolean> deleteLikes(Long id, Long filterId) {
    filterService.dislikeFilter(id, filterId);
    return SuccessResponse.of();
  }

  @GetMapping("/{filterId}/descriptions")
  public SuccessResponse<FilterDescriptionDto> getDescriptions(Long id, Long filterId) {
    return SuccessResponse.of(filterService.getFilterDescriptions(filterId));
  }
}
