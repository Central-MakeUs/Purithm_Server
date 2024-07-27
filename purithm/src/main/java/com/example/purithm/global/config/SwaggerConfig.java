package com.example.purithm.global.config;

import com.example.purithm.global.response.ErrorResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import java.util.List;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    SecurityScheme apiKey = new SecurityScheme()
        .type(Type.HTTP)
        .in(In.HEADER)
        .name("Authorization")
        .scheme("Bearer")
        .bearerFormat("JWT");

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearer-key");

    return new OpenAPI()
        .info(new Info()
            .title("Purithm API")
            .version("1.0.0")
            .description("Purithm API 문서입니다."))
        .components(new Components()
            .addSecuritySchemes("bearer-key", apiKey)
        ).addSecurityItem(securityRequirement);
  }

  @Bean
  public OpenApiCustomizer openApiCustomiser() {
    return openApi -> openApi.getPaths().values()
        .forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
          ApiResponse unauthorizedResponse = new ApiResponse()
              .description("유저 인증 실패")
              .content(new Content().addMediaType("application/json",
                  new MediaType().schema(new Schema<ErrorResponse>()
                      .type("object")
                          .addProperties("message", new Schema<String>().type("string"))
                          .addProperties("code", new Schema<Integer>().type("integer")))));

          ApiResponse notFoundResponse = new ApiResponse()
              .description("리소스를 찾을 수 없음")
              .content(new Content().addMediaType("application/json",
                  new MediaType().schema(new Schema<ErrorResponse>()
                      .type("object")
                      .addProperties("message", new Schema<String>().type("string"))
                      .addProperties("code", new Schema<Integer>().type("integer")))));

          operation.getResponses().addApiResponse("401", unauthorizedResponse);
          operation.getResponses().addApiResponse("404", notFoundResponse);

          List<Parameter> parameters = operation.getParameters();
          if (parameters != null) {
            operation.getParameters().removeIf(param -> param.getName().equals("LoginInfo"));
          }
        }));
    };
}
