package com.example.purithm.auth.service;

import com.example.purithm.auth.entity.CustomOAuth2User;
import com.example.purithm.user.entity.User;
import com.example.purithm.user.repository.UserRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
    Map<String, Object> properties = oAuth2User.getAttribute("properties");
    String id = oAuth2User.getAttributes().get("id").toString();

    String username = provider+" "+id;
    User existUser = userRepository.findByUsername(username);

    if (existUser == null) {
      User user = User.builder()
          .profile((String) properties.get("thumbnail_image"))
          .nickname((String) properties.get("nickname"))
          .username(username)
          .build();

      userRepository.save(user);
    }

    return new CustomOAuth2User(username);
  }

}
