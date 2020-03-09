package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void LombokTest(){
        String name = "TEST";
        int amount = 1000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name,amount);
        /**
         * assertJ의 assertThat 을 사용  테스트 검증 라이브러리의 검증 메소드 이다.
         *
         * Junit의 aseertTHat도 있지만 사용한 이유는
         * 1.CoreMatchers와 달리 추가적으로 라이브러릴가 필요하지 않다.
         *  -Junit을 쓰면 is()와 같이 CoreMatchers 라이브러리가 필요하다.
         *
         */

        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }
}
