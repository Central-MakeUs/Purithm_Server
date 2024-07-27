package com.example.purithm.domain.photographer.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;

public record PhotographerDto(
    @Schema(description = "작가 id")
    Long id,
    @Schema(description = "작가 이름")
    String name,
    @Schema(description = "작가 프로필")
    String profile,
    @Schema(description = "작가 설명")
    String description,
    @Schema(description = "작가 사진들")
    List<String> picture,
    @Schema(description = "작가 가입 일자")
    Date createdAt
) {

}
