package com.example.purithm.domain.photographer.response;

import java.util.Date;
import java.util.List;

public record PhotographerDto(
    Long id,
    String name,
    String profile,
    String description,
    List<String> picture,
    Date createdAt
) {

}
