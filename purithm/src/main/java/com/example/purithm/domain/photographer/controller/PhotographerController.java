package com.example.purithm.domain.photographer.controller;

import com.example.purithm.domain.photographer.response.PhotographerDto;
import com.example.purithm.domain.photographer.response.PhotographerFilterDto;
import com.example.purithm.global.response.SuccessResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/photographers")
public class PhotographerController implements PhotographerControllerDocs {
  @GetMapping
  public SuccessResponse<List<PhotographerDto>> getPhotographers(String authorization, String sortedBy) {
    return null;
  }

  @GetMapping("/{photographerId}/filters")
  public SuccessResponse<List<PhotographerFilterDto>> getFiltersByPhotographer(
      String authorization, Long photographerId, String sortedBy) {
    return null;
  }
}
