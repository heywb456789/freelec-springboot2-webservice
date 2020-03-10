package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자 자동추가 Public Posts(){}와 같다
@Entity //테이블과 링크될 클래스임을 나타낸다 , 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이및 으로 테이블을 매칭
public class Posts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //springboot2.0 부터 autoIncreasement 사용 위해 
    private Long id;
    
    @Column(length = 500, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    
    private String author;
    
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
