package com.example.purithm.user.config;

import com.example.purithm.user.filter.JWTFilter;
import com.example.purithm.user.handler.OAuth2AuthenticationSuccessHandler;
import com.example.purithm.user.jwt.JWTUtil;
import com.example.purithm.user.service.OAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final OAuth2UserService oAuth2UserService;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
  private final JWTUtil jwtUtil;

  public SecurityConfig(OAuth2UserService oAuth2UserService, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, JWTUtil jwtUtil) {
    this.oAuth2UserService = oAuth2UserService;
    this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
    this.jwtUtil = jwtUtil;
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .formLogin(formLogin -> formLogin.disable())
        .httpBasic(httpBasic -> httpBasic.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
        )
        .oauth2Login(oauth2Login -> oauth2Login
            .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                .baseUri("/oauth2/authorization")
            )
            .redirectionEndpoint(redirectionEndpoint -> redirectionEndpoint
                .baseUri("/login/oauth2/code/*")
            )
            .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserService))
            .successHandler(oAuth2AuthenticationSuccessHandler)
        );

    return http.build();
  }
}
