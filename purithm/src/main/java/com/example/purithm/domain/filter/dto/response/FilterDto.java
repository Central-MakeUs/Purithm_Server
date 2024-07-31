package com.example.purithm.domain.filter.dto.response;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.Membership;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilterDto(
    @Schema(description = "필터 id")
    Long id,
    @Schema(description = "필터 멤버십 타입", example = "basic")
    Membership membership,
    @Schema(description = "필터 이름")
    String name,
    @Schema(description = "필터 썸네일")
    String thumbnail,
    @Schema(description = "작가 id")
    Long photographerId,
    @Schema(description = "작가 이름")
    String photographerName,
    @Schema(description = "좋아요 수")
    int likes

) {
    public static FilterDto of(Filter filter) {
        return FilterDto.builder()
            .id(filter.getId())
            .membership(filter.getMembership())
            .name(filter.getName())
            .thumbnail(filter.getThumbnail())
            .photographerId(filter.getPhotographer().getId())
            .photographerName(filter.getPhotographer().getUsername())
            .likes(filter.getLikes())
            .build();
    }
}
