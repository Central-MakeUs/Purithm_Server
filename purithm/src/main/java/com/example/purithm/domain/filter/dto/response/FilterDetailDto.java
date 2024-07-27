package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public abstract class FilterDetailDto {
    @Schema(description = "필터 이름")
    String name;
    @Schema(description = "작가 id")
    Long photographerId;
    @Schema(description = "작가 이름")
    String photographerName;
    @Schema(description = "리뷰 수")
    int reviews;
    @Schema(description = "퓨어 지수")
    int pureDegree;
    @Schema(description = "필터 사진")
    List<String> pictures;
}
