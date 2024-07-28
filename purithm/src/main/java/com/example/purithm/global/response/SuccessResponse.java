package com.example.purithm.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "성공 응답")
@Builder
public class SuccessResponse<T> {
  @Schema(description = "HTTP 상태 코드")
  private final int code;
  @Schema(description = "응답 메시지")
  private final String message;
  @Schema(description = "응답 데이터")
  private T data;

  public static SuccessResponse of() {

    return new SuccessResponse<>(HttpStatus.OK.value(), "ok");
  }

  public static <T> SuccessResponse<T> of(T data) {
    return new SuccessResponse<>(HttpStatus.OK.value(), "ok", data);
  }
}