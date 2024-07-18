package com.example.purithm.domain.review.response;

import java.util.Date;
import java.util.List;

public record ReviewDto(
    String content,
    String username,
    Date createdAt,
    String userProfile,
    List<String> pictures,
    int pureDegree
) {

}
