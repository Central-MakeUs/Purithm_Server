package com.example.purithm.domain.filter.dto.response;

import com.example.purithm.domain.filter.entity.AOSFilterDetail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AOSFilterDetailDto {
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
  @Schema(description = "색온드")
  private int temperature;
  @Schema(description = "선명도")
  private int clear;
  @Schema(description = "명료도")
  private int clarity;

  public static AOSFilterDetailDto of(AOSFilterDetail filterDetail) {
    return AOSFilterDetailDto.builder()
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
