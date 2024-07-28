package com.example.purithm.global.auth.controller;

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
import reactor.core.publisher.Mono;

public interface AuthControllerDocs {
  @Operation(
      summary = "Kakao Login",
      parameters = {
          @Parameter(name = "Authorization", description = "kakao access token을 보냅니다. Bearer token 형식입니다.", required = true, in = ParameterIn.HEADER)
      }
  )
  public Mono<SuccessResponse<String>> kakaoLogin(@RequestHeader("Authorization") String token);

  @Operation(
      summary = "Apple Login",
      parameters = {
          @Parameter(name = "Authorization", description = "Apple access token을 보냅니다. Bearer token 형식입니다.", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "string"))
      }
  )
  public SuccessResponse<String> appleLogin(
      @RequestHeader("Authorization") String token, @RequestBody String email)
      throws IOException, ParseException, JOSEException;
}
