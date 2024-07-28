package com.example.purithm.domain.feed.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import lombok.Getter;

@Schema(description = "피드 정보 응답 dto")
@Getter
public class FeedDto {
    @Schema(description = "필터 id")
    Long filterId;
    @Schema(description = "작가")
    String writer;
    @Schema(description = "작가 프로필")
    String profile;
    @Schema(description = "퓨어 지수")
    int pureDegree;
    @Schema(description = "피드 생성 날짜")
    Date createdAt;
    @Schema(description = "피드 사진들")
    List<String> pictures;
    @Schema(description = "필터 이름")
    String filterName;
}
