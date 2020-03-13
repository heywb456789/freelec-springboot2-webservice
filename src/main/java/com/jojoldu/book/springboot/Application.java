package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing//jpaAuditing 활성화한다.
@SpringBootApplication
public class Application {
    public static void main (String[] args){
        SpringApplication.run(Application.class,args);
    }
}
