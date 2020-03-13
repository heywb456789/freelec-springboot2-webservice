package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;
    /**
     *컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
     * 파라미터에 @LoginUser 와 SessionUser.class 인경우 true 반환
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //어노테이션이 적용 되었는지지
       boolean isLoginAnnotation = parameter.getParameterAnnotation(LoginUser.class) !=null;
        //class 타입 체크
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginAnnotation && isUserClass;
    }

    /**
     * 파라미터에 전달할 객체를 생성
     * 여기서는 세션에서 객체를 가져온다.
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return httpSession.getAttribute("user");
    }
}
