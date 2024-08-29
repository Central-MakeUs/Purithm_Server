package com.example.purithm.domain.feed.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;

import com.example.purithm.domain.filter.entity.OS;

import lombok.Builder;
import lombok.Getter;

@Schema(description = "피드 정보 응답 dto")
@Getter
@Builder
public class FeedDto {
    @Schema(description = "필터 id")
    Long filterId;

    @Schema(description = "필터 이름")
    String filterName;

    @Schema(description = "작가")
    String writer;

    @Schema(description = "작가 프로필")
    String profile;

    @Schema(description = "퓨어 지수")
    int pureDegree;

    @Schema(description = "피드 내용")
    String content;

    @Schema(description = "피드 생성 날짜")
    Date createdAt;

    @Schema(description = "피드 사진들")
    List<String> pictures;

    @Schema(description = "피드 id")
    Long id;

    @Schema(description = "필터 썸네일")
    String filterThumbnail;

    @Schema(description = "필터 OS 정보")
    OS os;

    @Schema(description = "필터 조회 가능 여부")
    boolean canAccess;
}
