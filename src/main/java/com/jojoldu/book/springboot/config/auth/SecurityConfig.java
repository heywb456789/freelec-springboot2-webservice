package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //1
                .headers().frameOptions().disable() // 2  => 1,2 : h2-console 화면을 사용하기 위해 해당 옵션을 disable한다.
                .and()
                    .authorizeRequests() //URL 별 권한 관리를 설정하는 옵션의 시작점 , AuthorizeRequest가 선언 되어야 antMatcher를 사용할 수 있다.
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리 대상을 지정하는 옵션, URL, HTTP 메소드별 관리가 가능하다.
                                                                                    // , "/".등 지정된 URL 들은 permitAll() 옵션을 통해 전체 열람 권한을 주었다.
                                                                                    // /api/v1/** 주소를 가진 APi는 User 권한만 준다.
                    .anyRequest().authenticated() //설정된 값들 이외 나머지 URL 들을 나타낸다.
                                                    //여기서는authenticated()를 추가하여 나머지 URL 들은 모두 인증된 사용자들에게
                                                    //인증된 사용자 즉, 로그인한 유저
                .and()
                    .logout()
                        .logoutSuccessUrl("/")// 로그아웃 기능에 대한 여러 설정의 진입점
                .and()
                    .oauth2Login()// Oauth2의 로그인 기능에 대한 설정 진입점
                        .userInfoEndpoint()//Oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정등을 담당
                            .userService(customOAuth2UserService);//로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                                                                //리소스 서버 (즉, 소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
    }
}
