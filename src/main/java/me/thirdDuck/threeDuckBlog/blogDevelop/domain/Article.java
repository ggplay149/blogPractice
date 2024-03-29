package me.thirdDuck.threeDuckBlog.blogDevelop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity //엔티티(DB 테이블과 매핑되는 객체)로 지정
@Getter //게터
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 protected
public class Article {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동으로 1씩증가
    @Column(name = "id" ,updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate //엔티티가 생성될때 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate //엔티티가 생성될때 생성 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name="author", nullable = false)
    private String author;



    @Builder//빌더패던으로 생성가능하게 해줌줌
    public Article(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
