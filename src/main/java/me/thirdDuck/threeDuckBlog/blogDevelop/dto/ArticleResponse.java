package me.thirdDuck.threeDuckBlog.blogDevelop.dto;

import lombok.Getter;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;

@Getter
public class ArticleResponse {

    private String title;
    private String content;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
