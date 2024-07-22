package com.example.purithm.domain.filter.dto.response;

public record FilterDto(
    Long id,
    String name,
    String thumbnail,
    Long photographerId,
    String photographerName,
    int likes

) {

}
