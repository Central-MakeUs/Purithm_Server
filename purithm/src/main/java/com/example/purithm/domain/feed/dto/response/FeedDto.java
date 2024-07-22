package com.example.purithm.domain.feed.dto.response;

import java.util.Date;
import java.util.List;

public record FeedDto(
    String writer,
    String profile,
    int pureDegree,
    Date createdAt,
    List<String> pictures,
    String filterName,
    Long filterId
) {

}
