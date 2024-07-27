package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record PhotographerDescriptionDto(
    @Schema(description = "작가 이름")
    String name,
    @Schema(description = "작가의 말 사진")
    List<PhotoDescriptionDto> details,
    @Schema(description = "작가의 말 태그")
    List<String> tags
) {

}
