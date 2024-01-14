package me.thirdDuck.threeDuckBlog.blogDevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;

@NoArgsConstructor //기본생성자
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(String author){
        return Article.builder().title(title).content(content).author(author).build();
    }

}
