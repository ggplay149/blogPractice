package me.thirdDuck.threeDuckBlog.controller;

import lombok.RequiredArgsConstructor;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.AddArticelRequest;
import me.thirdDuck.threeDuckBlog.blogDevelop.repository.BlogRepository;
import me.thirdDuck.threeDuckBlog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor //final이 붙거나 @Notnull이 붙은 필드의 생성자 추가
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반황하는 컨트롤러
public class BlogApiController{

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticel(@RequestBody AddArticelRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);    //요청상태가 성공적일때, 이후 수행
    }
}
