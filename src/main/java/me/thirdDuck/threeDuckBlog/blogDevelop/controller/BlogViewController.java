package me.thirdDuck.threeDuckBlog.blogDevelop.controller;

import lombok.RequiredArgsConstructor;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.ArticleListViewResponse;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.ArticleViewResponse;
import me.thirdDuck.threeDuckBlog.blogDevelop.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    //글 목록 조회
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles",articles);
        return "articleList";
    }

    // 글 조회
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article",new ArticleViewResponse(article));
        return "article";
    }

    //글 생성
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required=false) Long id, Model model){
        if(id == null){
            model.addAttribute("article",new ArticleViewResponse());
        }else{
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
