package com.example.purithm.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record ReviewRequestDto(
    @Schema(description = "필터 id")
    Long filterId,
    @Schema(description = "퓨어 지수")
    int pureDegree,
    @Schema(description = "리뷰 내용")
    String content,
    @Schema(description = "리뷰 사진들")
    List<String> pictures

) {

}
