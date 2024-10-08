package com.example.purithm.domain.filter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class AOSFilterDetail {
  @Id
  @Column(name = "filter_id", nullable = false)
  private Long id;
  @OneToOne
  @JoinColumn(name = "filter_id")
  @MapsId
  private Filter filter;
  private int lightBalance; // 라이트 밸런스
  private int brightness; // 밝기
  private int exposure; // 노출
  private int contrast; // 대비
  private int highlight; // 하이라이트
  private int shadow; // 그림자
  private int saturation; // 채도
  private int tint; // 틴트
  private int temperature; // 색온드
  private int clear; // 선명도
  private int clarity; // 명료도
}