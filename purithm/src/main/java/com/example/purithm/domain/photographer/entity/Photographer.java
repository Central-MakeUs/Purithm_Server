package com.example.purithm.domain.photographer.entity;

import com.example.purithm.global.converter.StringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Photographer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String profile;
  private String description;
  @Convert(converter = StringListConverter.class)
  private List<String> thumbnails;
}
