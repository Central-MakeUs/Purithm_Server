package com.example.purithm.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record ReviewResponseDto (
    @Schema(description = "리뷰 내용")
    String content,
    @Schema(description = "작성자")
    String username,
    @Schema(description = "작성일")
    Date createdAt,
    @Schema(description = "작성자 프로필")
    String userProfile,
    @Schema(description = "리뷰 사진")
    List<String> pictures,
    @Schema(description = "퓨어 지수")
    int pureDegree
) {

}
