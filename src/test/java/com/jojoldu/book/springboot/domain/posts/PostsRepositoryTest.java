package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    /**
     *junit 에서 단위 테스트가 끝날떄만다 수행되는 메소드를 지정
     * 보통은 배포 전 전체 테스트를 수행할 떄 테스트간 데이터 침범을 막기 위해 사용
     * 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H@에 데이터가 그대로 남아 다음 실행 시 테스트 실패할 수 있다.
     */
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void saveBoardArticle(){
        String title = "Test게시글";
        String content = "테스트 전문 ";
        //posts 에 insert,/update 실행
        //id 값이 없으면 insert, 없으면 update
        postsRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("heywb457@gmail.com")
                            .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>> createDate = " +posts.getCreatedDate() +", modifiedDate = " +posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
