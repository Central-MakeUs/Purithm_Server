package com.example.purithm.domain.filter.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class iOSFilterDetailDto extends FilterDetailDto {
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
}
