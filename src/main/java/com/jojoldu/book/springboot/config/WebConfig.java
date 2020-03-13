package com.jojoldu.book.springboot.config;

import com.jojoldu.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver resolver;

    /**
     * HandlerMethodArgumentResolver 는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야 합니다.
     * HandlerMethodArgumentResolver은 컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스입니다.
     *
     * @PathVariable 어노테이션을 사용해 Request 의 Path Parameter 값을 받아올 때 이 HandlerMethodArgumentResolver를 사용해서 값을 받아옵니다.
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(resolver);
    }
}
