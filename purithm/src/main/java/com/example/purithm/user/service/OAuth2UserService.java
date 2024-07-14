package com.example.purithm.user.service;

import com.example.purithm.user.entity.User;
import com.example.purithm.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails()
        .getUserInfoEndpoint()
        .getUserNameAttributeName();

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
    return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
  }

}
