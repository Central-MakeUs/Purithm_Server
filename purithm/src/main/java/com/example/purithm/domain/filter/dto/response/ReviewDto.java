package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

public record ReviewDto(
    @Schema(name = "퓨어 지수")
    int pureDegree,
    @Schema(name = "필터 이름")
    String filter,
    @Schema(name = "후기 생성 일자")
    Date createdAt
) {
}
