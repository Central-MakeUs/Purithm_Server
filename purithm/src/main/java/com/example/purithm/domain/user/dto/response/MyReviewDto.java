package com.example.purithm.domain.user.dto.response;

import java.util.Date;
import java.util.List;

public record MyReviewDto(
    String content,
    String username,
    Date createdAt,
    String userProfile,
    List<String> pictures,
    int pureDegree
) {

}
