package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @RunnWith
 *  build.gradle의 TEST Dependencies 참조
 *  1. 테스트를 진행할때 Junit 에 내장된 실행자 외에 다른 실행자를 실행한다.
 *  2. 여기서는 SpringRunner 라는 실행자를 실행한다.
 *  3. 스프링 부트 테스트와 Junit 사이에 연결자
 */

/**
 * @WebMvcTest
 * 1.여러 스프링 테스트 어노테이션중 WEB(Srpgin Mvc)에 집중할 수 있는 어노테이션
 * 2.선언할 경우 @Controller , @ControllerAdvice 등을 사용할 수 있다.
 *  단, @service, @Component, @Repository등은 사용 불가
 * 3.여기서는 Controller 만 사용하기 떄문에 선언한다.
 */

/**
 * ERRor :
 *   java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 * ==> @SpringBootTest 클래스가 실행되면서 필요한 스프링부트 설정 클래스를 찾지 못할 경우
 * 흔히, 테스트 클래스를 작성하면서 테스트 클래스 패키지명을 메인 클래스 패키지와 차이가 생기면서 이 에러를 만난다.
 *
 * 보통 클래스 찾는 경로 .
 * 1. com.jojoldu.book.springboot.web
 * 2. com.jojoldu.book.springboot
 * 3. com.jojoldu.book
 * 4. com.jojoldu
 * 5. com
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
            excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                                    classes = SecurityConfig.class)
            }) //customOAuth2UserService를 스캔하지 않는다.
public class HelloControllerTest {
    //웹 API 테스트할떄 사용
    //MVC의 테스트 시작점
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다()throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))//mockmvc를 통해 /hello 주소로 Http Get 호출
                .andExpect(status().isOk())//mvc.perform 의 결과를 검증
                .andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void CheckDtoValue() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
