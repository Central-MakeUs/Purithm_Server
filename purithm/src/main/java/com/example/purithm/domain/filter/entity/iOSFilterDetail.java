package com.example.purithm.domain.filter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class iOSFilterDetail {
  @Id
  @OneToOne
  @JoinColumn(name = "filter_id")
  private Filter filter;
  private int exposure; // 노출
  private int luminance; //휘도
  private int highlight; // 하이라이트
  private int shadow; // 그림자
  private int contrast; // 대비
  private int brightness; // 밝기
  private int blackPoint; // 블랙포인트
  private int saturation; // 채도
  private int colorfulness; // 색 선명도
  private int warmth; // 따뜻함
  private int hue; // 색조
  private int clear; // 선명도
  private int clarity; // 명료도
  private int noise; // 노이즈 감소
  private int vignette; // 비네트
}
