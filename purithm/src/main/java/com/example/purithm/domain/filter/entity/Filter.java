package com.example.purithm.domain.filter.entity;

import com.example.purithm.domain.photographer.entity.Photographer;
import com.example.purithm.global.converter.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
public class Filter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String thumbnail;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "photographer_id", nullable = false)
  private Photographer photographer;
  private int likes;
  private int price;
  @Temporal(TemporalType.DATE)
  private Date createdAt;
  int pureDegree;
  @Convert(converter = StringListConverter.class)
  private List<String> pictures;
  private Membership membership;

  private OS os;
}
