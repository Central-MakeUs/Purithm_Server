package com.example.purithm.global.auth.controller;

import com.example.purithm.global.auth.dto.request.LoginRequestDto;
import com.example.purithm.global.auth.dto.request.SignUpRequestDto;
import com.example.purithm.global.auth.dto.response.LoginDto;
import com.example.purithm.global.response.SuccessResponse;
import com.nimbusds.jose.JOSEException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;

public interface AuthControllerDocs {
    @Operation(
        summary = "자체 회원 가입"
    )
    public SuccessResponse signup(@RequestBody SignUpRequestDto signUpRequestDto);

    @Operation(
        summary = "자체 로그인"
    )
    public SuccessResponse<LoginDto> login(@RequestBody LoginRequestDto loginRequestDto);

    @Operation(
      summary = "Kakao Login",
      parameters = {
          @Parameter(name = "Authorization", description = "kakao access token을 보냅니다. Bearer token 형식입니다.", required = true, in = ParameterIn.HEADER)
      })
    public Mono<SuccessResponse<LoginDto>> kakaoLogin(@RequestHeader("Authorization") String token);

    @Operation(
      summary = "Apple Login",
      parameters = {
          @Parameter(name = "Authorization", description = "Apple access token을 보냅니다. Bearer token 형식입니다.", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "string"))
      })
    public SuccessResponse<LoginDto> appleLogin(
      @RequestHeader("Authorization") String token, @RequestParam(value = "username", required = false) String username)
      throws IOException, ParseException, JOSEException;
}
