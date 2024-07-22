package com.example.purithm.domain.review.dto.request;

import java.util.List;

public record ReviewRequestDto(
    int pureDegree,
    String content,
    List<String> picture

) {

}
