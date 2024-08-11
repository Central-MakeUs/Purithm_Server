package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.FilterDetail;
import com.example.purithm.domain.photographer.entity.Photographer;

@Builder
public class FilterDescriptionDto {
    @Schema(description = "작가 id")
    Long photographerId;

    @Schema(description = "작가 이름")
    String name;

    @Schema(description = "필터 소개")
    String description;

    @Schema(description = "필터 소개 사진들")
    List<PhotoDescription> photoDescriptions;

    @Schema(description = "필터 태그")
    List<String> tag;

    public static FilterDescriptionDto of(Filter filter) {
        Photographer photographer = filter.getPhotographer();

        return FilterDescriptionDto.builder()
            .photographerId(photographer.getId())
            .name(photographer.getUsername())
            .description(filter.getDescription())
            .photoDescriptions(PhotoDescription.of(filter.getFilterDetails()))
            .tag(filter.getHashTag())
            .build();
    }

    @Getter
    @Builder
    public static class PhotoDescription {
        @Schema(description = "필터 소개 사진 제목")
        String title;
        @Schema(description = "필터 소개 사진 설명")
        String description;
        @Schema(description = "필터 소개 사진")
        String picture;

        public static List<PhotoDescription> of(List<FilterDetail> filterDetails) {
            return filterDetails.stream().map(filterDetail ->
                PhotoDescription.builder()
                    .title(filterDetail.getTitle())
                    .description(filterDetail.getDescription())
                    .picture(filterDetail.getPicture())
                    .build()).toList();
        }
    }
}
