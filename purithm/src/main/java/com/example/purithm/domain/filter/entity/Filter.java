package com.example.purithm.domain.filter.entity;

import com.example.purithm.domain.photographer.entity.Photographer;
import com.example.purithm.global.converter.FilterDescriptionListConverter;
import com.example.purithm.global.converter.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Filter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String thumbnail;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "photographer_id", nullable = false)
  private Photographer photographer;

  private int price;

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  @Column(updatable = false)
  private Date createdAt;

  @Column(columnDefinition = "TEXT")
  @Convert(converter = FilterDescriptionListConverter.class)
  private List<FilterDetail> filterDetails;

  private String description;

  private Membership membership;

  private OS os;

  private Tag tag;

  @Convert(converter = StringListConverter.class)
  private List<String> hashTag;
}
