package com.example.purithm.domain.review.dto.response;

import java.util.Date;
import java.util.List;

public record ReviewResponseDto (
    String content,
    String username,
    Date createdAt,
    String userProfile,
    List<String> pictures,
    int pureDegree
) {

}
