package com.example.purithm.domain.user.dto.response;

public record MyFilterDto(
  String type,
  String name,
  String photographer,
  Long reviewId
) {

}
