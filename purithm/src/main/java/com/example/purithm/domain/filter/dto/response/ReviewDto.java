package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Date;
import java.util.List;

import com.example.purithm.domain.review.entity.Review;

@Builder
public record ReviewDto(
    @Schema(description = "후기 id")
    Long id,
    @Schema(description = "퓨어 지수")
    int pureDegree,
    @Schema(description = "후기 작성자 이름")
    String user,
    @Schema(description = "후가 작성자 프로필")
    String profile,
    @Schema(description = "후기 내용")
    String content,
    @Schema(description = "후기 생성 일자")
    Date createdAt,
    @Schema(description = "후기 사진")
    List<String> pictures
) {
    public static ReviewDto of(Review review) {
        return ReviewDto.builder()
            .id(review.getId())
            .pureDegree(review.getPureDegree())
            .user(review.getUser().getUsername())
            .profile(review.getUser().getProfile())
            .content(review.getContent())
            .createdAt(review.getCreatedAt())
            .pictures(review.getPictures())
            .build();
    }
}
