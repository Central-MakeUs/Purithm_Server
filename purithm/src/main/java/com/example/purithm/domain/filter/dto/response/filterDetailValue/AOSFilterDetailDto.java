package com.example.purithm.domain.filter.dto.response.filterDetailValue;

import com.example.purithm.domain.filter.entity.AOSFilterDetail;
import com.example.purithm.domain.filter.entity.Filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AOSFilterDetailDto {

  @Schema(description = "id")
  private Long id;

  @Schema(description = "filter 썸네일")
  private String thumbnail;

  @Schema(description = "filter 좋아요 여부")
  private boolean liked;

  @Schema(description = "filter 이름")
  private String name;

  @Schema(description = "filter 보정 상세 값")
  private FilterValue value;

  public static AOSFilterDetailDto of(Filter filter, boolean liked, AOSFilterDetail filterDetail) {
    return AOSFilterDetailDto.builder()
        .id(filter.getId())
        .thumbnail(filter.getThumbnail())
        .liked(liked)
        .name(filter.getName())
        .value(FilterValue.of(filterDetail)).build();
  }

  @Getter
  @Builder
  public static class FilterValue {
    @Schema(description = "라이트 밸런스")
    private int lightBalance;
    @Schema(description = "밝기")
    private int brightness;
    @Schema(description = "노출")
    private int exposure;
    @Schema(description = "대비")
    private int contrast;
    @Schema(description = "하이라이트")
    private int highlight;
    @Schema(description = "그림자")
    private int shadow;
    @Schema(description = "채도")
    private int saturation;
    @Schema(description = "틴트")
    private int tint;
    @Schema(description = "색온도")
    private int temperature;
    @Schema(description = "선명도")
    private int clear;
    @Schema(description = "명료도")
    private int clarity;

    public static FilterValue of(AOSFilterDetail filterDetail) {
      return FilterValue.builder()
          .lightBalance(filterDetail.getLightBalance())
          .brightness(filterDetail.getBrightness())
          .exposure(filterDetail.getExposure())
          .contrast(filterDetail.getContrast())
          .highlight(filterDetail.getHighlight())
          .shadow(filterDetail.getShadow())
          .saturation(filterDetail.getSaturation())
          .tint(filterDetail.getTint())
          .temperature(filterDetail.getTemperature())
          .clear(filterDetail.getClear())
          .clarity(filterDetail.getClarity())
          .build();
    }
  }

}