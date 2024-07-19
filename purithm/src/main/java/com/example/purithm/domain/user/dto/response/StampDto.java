package com.example.purithm.domain.user.dto.response;

import java.util.Date;

public record StampDto(
    String name,
    String photographer,
    String profile,
    Date createdAt,
    Long filterId
) {
}
