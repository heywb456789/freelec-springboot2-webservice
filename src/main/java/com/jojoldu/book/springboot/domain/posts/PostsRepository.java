package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//보통 마이바티스등에서 부르는 DAO 라는 DBLayer 접근자이다.
//JPA 에서는 Repository라 불리우며 인터페이스로 생성한다.
//jpaRepository<Entity 클래스, PK타입> => CRUD 자동 적용
//Entity 클래스와 기본 EntityRepository 위치는 같아야한다.
public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p,id DESC")
    List<Posts> findAllDesc();
}
