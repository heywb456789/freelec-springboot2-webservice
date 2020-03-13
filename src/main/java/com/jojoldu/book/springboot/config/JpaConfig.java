package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //JPA Auditing 활성화
//TEST 사용하기 위해 Application.java에서 제외하고 새롭게 만든다.
public class JpaConfig {
}
