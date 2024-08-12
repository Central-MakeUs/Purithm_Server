package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.photographer.service.PhotographerService;
import com.example.purithm.domain.photographer.dto.response.PhotographerDto;
import com.example.purithm.domain.photographer.dto.response.PhotographerFilterDto;
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

  @GetMapping
  public SuccessResponse<List<PhotographerDto>> getPhotographers(Long id, String sortedBy) {
    return SuccessResponse.of(photographerService.getPhotographerList(sortedBy));
  }

  @GetMapping("/{photographerId}/filters")
  public SuccessResponse<List<PhotographerFilterDto>> getFiltersByPhotographer(
      Long id, Long photographerId, String sortedBy) {
    return null;
  }
}
