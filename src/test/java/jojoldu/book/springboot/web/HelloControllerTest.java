package jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.HelloController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  build.gradle의 TEST Dependencies 참조
 *  1. 테스트를 진행할때 Junit 에 내장된 실행자 외에 다른 실행자를 실행한다.
 *  2. 여기서는 SpringRunner 라는 실행자를 실행한다.
 *  3. 스프링 부트 테스트와 Junit 사이에 연결자
 */
@RunWith(SpringRunner.class)
/**
 * 1.여러 스프링 테스트 어노테이션중 WEB(Srpgin Mvc)에 집중할 수 있는 어노테이션
 * 2.선언할 경우 @Controller , @ControllerAdvice 등을 사용할 수 있다.
 */
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
}
