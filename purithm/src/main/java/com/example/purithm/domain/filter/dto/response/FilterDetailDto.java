package com.example.purithm.domain.filter.dto.response;

import java.util.List;

public record FilterDetailDto(
    String name,
    int lightness,
    int exposure,
    int luminance,
    int colorfulness,
    int shadow,
    int highlight,
    Long photographerId,
    String photographerName,
    int reviews,
    int pureDegree,
    List<String> pictures
) {

}
