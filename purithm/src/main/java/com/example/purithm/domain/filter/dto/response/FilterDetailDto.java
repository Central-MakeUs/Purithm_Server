package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Schema(description = "필터 상세 정보")
@Getter
@Builder
public class FilterDetailDto {
    @Schema(description = "필터 이름")
    String name;
    @Schema(description = "좋아요 수")
    int likes;
    @Schema(description = "퓨어 지수")
    int pureDegree;
    @Schema(description = "필터 사진")
    List<FilterPictureDto> pictures;
    @Schema(description = "좋아요 여부")
    boolean liked;
}
