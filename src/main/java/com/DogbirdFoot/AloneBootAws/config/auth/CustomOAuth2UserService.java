package com.DogbirdFoot.AloneBootAws.config.auth;

import com.DogbirdFoot.AloneBootAws.config.auth.dto.OAuthAttributes;
import com.DogbirdFoot.AloneBootAws.config.auth.dto.SessionUser;
import com.DogbirdFoot.AloneBootAws.domain.user.NormalUser;
import com.DogbirdFoot.AloneBootAws.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //로그인중인 서비스를 구분하는 코드
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails() //로그인진행시 키값
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());
        NormalUser normalUser = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(normalUser));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(normalUser.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    private NormalUser saveOrUpdate(OAuthAttributes attributes){
        NormalUser normalUser = userRepository.findByEmail(attributes.getEmail())
                .map( entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(normalUser);
    }
}
