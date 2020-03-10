package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/**
 * 세 종류를 중복해서 사용할 수 있으며 어노테이션 이름 그대로
 * @NoArgsConstructor는 파라미터가 없는 생성자,
 * @AllArgsConstructor는 모든 필드를 파라미터로 가지는 생성자를 만든다.
 * @RequiredArgsConstrucotr는 기본 값이 없고 @NonNull 어노테이션이 붙은 필드를 파라미터로 입력받는 생성자를 만든다..
 */
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title,String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    //TODO : Entity 클래스란 ??  Entity 클래스를 Request와 Response 클래스로 사용해서는 안된다.
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
