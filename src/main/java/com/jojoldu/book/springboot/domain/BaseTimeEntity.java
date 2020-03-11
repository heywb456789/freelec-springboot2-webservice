package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들을 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)//auditing 기능을 주입한다.
public class BaseTimeEntity {

    @CreatedDate //Entity에 값을 생성한 시간이 적용
    private LocalDateTime createdDate;

    @LastModifiedDate//Entity에 값이 변경된 시간을 저장
    private LocalDateTime modifiedDate;
}
