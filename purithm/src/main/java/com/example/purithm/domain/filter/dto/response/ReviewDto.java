package com.example.purithm.domain.filter.dto.response;

import java.util.Date;

public record ReviewDto(
    int pureDegree,
    String photographer,
    Date createdAt
) {
}
