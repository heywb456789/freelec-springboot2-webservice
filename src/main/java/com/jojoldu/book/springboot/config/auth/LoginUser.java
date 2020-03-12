package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// 어노테이션이 생성될 수 있는 위치를 조정한다
//Parameter로 지정하여 메소드의 파라미터로 선언된 객체에서만 사용 가능
//클래스 선언문에 사용가능한 TYPE 등이 있다.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//@interface 이 파일을 어노테이션 클래스로 지정한다.
//LoginUser라는 이름을 가진 어노테이션이 생성된다고 보면된다.
public @interface LoginUser {
}
