package com.example.purithm.domain.photographer.response;

public record PhotographerFilterDto(
    Long id,
    String name,
    String thumbnail,
    Long photographerId,
    String photographerName,
    int likes
) {

}
