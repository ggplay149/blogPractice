package me.thirdDuck.threeDuckBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //created_at, updated_at 자동 업데이트
@SpringBootApplication //메인 클래스 기능
public class threeDuckBlogApplication {
    public static void main(String[] args){
        SpringApplication.run(threeDuckBlogApplication.class, args);
    }
}
