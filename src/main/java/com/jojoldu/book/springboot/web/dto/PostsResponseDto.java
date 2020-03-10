package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    /**
     * Response DTo 는 Entity 필드  중 일부만 사용하므로 생성자로  Entity 값을 받아 넣는다
     * 굳이 모든 필드를 가진 생성자가 필요하지 않는다.
     *
     */
    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
