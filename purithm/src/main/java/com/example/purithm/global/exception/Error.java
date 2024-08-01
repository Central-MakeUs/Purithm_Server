package com.example.purithm.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {
  /* 400 */
  BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, 40000, "적절하지 않은 요청입니다."),
  NOT_ACCEPTABLE_OS(HttpStatus.BAD_REQUEST, 40001, "지원하지 않는 OS 기종입니다."),

  /* 401 */
  INVALID_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, 40100, "유효하지 않은 토큰입니다."),
  EXPIRED_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, 40101, "만료된 토큰입니다."),

  /* 403 */
  NOT_AGREED_TERM(HttpStatus.FORBIDDEN, 40300, "이용약관 동의가 필요합니다"),

  /* 404 */
  NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 40400, "리소스를 찾을 수 없습니다."),

  /* 405 */
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 40500, "존재하지 않는 요청입니다."),

  /* 409 */
  NICKNAME_ALREADY_USED_ERROR(HttpStatus.CONFLICT, 40900, "이미 사용 중인 닉네임입니다."),

  /* 500 */
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "서버 에러가 발생했습니다."),
  EXTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50001, "외부 API와 통신에 실패했습니다.");


  private final HttpStatus httpStatus;
  private final int code;
  private final String message;
}
