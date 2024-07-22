package com.example.purithm.domain.filter.dto.response;

import java.util.List;

public record PhotographerDescriptionDto(
    String name,
    List<PhotoDescriptionDto> details,
    List<String> tags
) {

}
