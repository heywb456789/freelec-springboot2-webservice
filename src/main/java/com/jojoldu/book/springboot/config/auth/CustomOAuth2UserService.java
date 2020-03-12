package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.OAuthAttributes;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
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

/**
 * 구글 로그인 이후 가져온 사용자의 정보 (email, name , picture)들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원한다.
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        // 현재 로그인 진행중인 서비스를 구분하는 코드
        // 지금은 구글만 사용하는 불필요한 값, 이후 네이버 연동하고 네이버 로그인 인지, 구글 로그인인지 구분하는 값
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //UserNameAttributeName
        //Oauth2 로그인 진행 시 키가 되는 필드 값이다. PrimaryKey와 같은 역할
        //구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않는다. 구글의 기본코드는 "sub"
        //네이버 로그인과 구글 로그인을 동시 지원할때 사용한다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //OAuthAttributes
        //Oauth2UserService를 통해 가져온 OAUth2User의 Attribute를 담을 클래스이다.
        //이후 네이버 등 다른 소셜 로그인도 이클래스를 사용한다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        //SessionUser
        //세션에 사용자 정보를 저장하기 위한 DTO
        //"User"Class 를 사용하지 않고 새로 만들어 사용하는 이유 => Error 발생
        //failed to convert from type [java.lang.Object] to type....
        //위의 의미는 직렬화를 구현하지 않았다는 의미이다.
        //User Class 는 Entity 이기 때문에 다른 관계가 언제든지 발생할수 있다 따라서 직렬화 기능을 가진 세션 DTO를 추가 생성한다.
        // 직렬화 : 자바 직렬화란 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte)
        // 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)을 아울러서 이야기합니다.
        //자바 기본(primitive) 타입과 java.io.Serializable 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건을 가집니다.
        httpSession.setAttribute("user",new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),attributes.getAttributes(),attributes.getNameAttributeKey());
    }
    //User 정보가 업데이트 되면 update 기능도 같이 대비
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user =userRepository.findByEmail(attributes.getEmail())
                            .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                            .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
