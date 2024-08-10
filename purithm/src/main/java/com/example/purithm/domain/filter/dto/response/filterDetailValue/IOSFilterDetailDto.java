package com.example.purithm.domain.filter.dto.response.filterDetailValue;

import com.example.purithm.domain.filter.entity.Filter;
import com.example.purithm.domain.filter.entity.IOSFilterDetail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IOSFilterDetailDto {

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

  public static IOSFilterDetailDto of(Filter filter, boolean liked, IOSFilterDetail filterDetail) {
    return IOSFilterDetailDto.builder()
        .id(filter.getId())
        .thumbnail(filter.getThumbnail())
        .liked(liked)
        .name(filter.getName())
        .value(IOSFilterDetailDto.FilterValue.of(filterDetail)).build();
  }

  @Getter
  @Builder
  public static class FilterValue {
    @Schema(description = "노출")
    private int exposure;
    @Schema(description = "휘도")
    private int luminance;
    @Schema(description = "하이라이트")
    private int highlight;
    @Schema(description = "그림자")
    private int shadow;
    @Schema(description = "대비")
    private int contrast;
    @Schema(description = "밝기")
    private int brightness;
    @Schema(description = "블랙포인트")
    private int blackPoint;
    @Schema(description = "채도")
    private int saturation;
    @Schema(description = "색 선명도")
    private int colorfulness;
    @Schema(description = "따뜻함")
    private int warmth;
    @Schema(description = "색조")
    private int hue;
    @Schema(description = "선명도")
    private int clear;
    @Schema(description = "명료도")
    private int clarity;
    @Schema(description = "노이즈 감소")
    private int noise;
    @Schema(description = "비네트")
    private int vignette;

    public static FilterValue of(IOSFilterDetail filterDetail) {
      return FilterValue.builder()
          .exposure(filterDetail.getExposure())
          .luminance(filterDetail.getLuminance())
          .highlight(filterDetail.getHighlight())
          .shadow(filterDetail.getShadow())
          .contrast(filterDetail.getContrast())
          .brightness(filterDetail.getBrightness())
          .blackPoint(filterDetail.getBlackPoint())
          .saturation(filterDetail.getSaturation())
          .colorfulness(filterDetail.getColorfulness())
          .warmth(filterDetail.getWarmth())
          .hue(filterDetail.getHue())
          .clear(filterDetail.getClear())
          .clarity(filterDetail.getClarity())
          .noise(filterDetail.getNoise())
          .vignette(filterDetail.getVignette())
          .build();
    }
  }
}
