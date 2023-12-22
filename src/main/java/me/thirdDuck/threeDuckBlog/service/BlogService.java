package me.thirdDuck.threeDuckBlog.service;

import lombok.RequiredArgsConstructor;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.AddArticelRequest;
import me.thirdDuck.threeDuckBlog.blogDevelop.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //final이 붙거나 @Notnull이 붙은 필드의 생성자 추가
@Service //빈등록
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticelRequest request){
        return blogRepository.save(request.toEntity());
    }
}
