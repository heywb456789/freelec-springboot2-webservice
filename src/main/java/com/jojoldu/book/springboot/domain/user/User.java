package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)// Jpa 데이터베이스로 저장시 Enum 값을 어떤 형태로 저장할지를 결정한다. 기본적으로 Int 숫자가 저장 => 코드의 의미를 띄기 위해 String 으로 저장 가능케 설정
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String name, String email, String picture, Role role){
        this.email = email;
        this.name = name;
        this. picture = picture;
        this.role = role;
    }

    public User update (String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
