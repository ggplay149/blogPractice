package me.thirdDuck.threeDuckBlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.AddArticelRequest;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.UpdateArticleRequest;
import me.thirdDuck.threeDuckBlog.blogDevelop.repository.BlogRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//테스트용 어플리케이션 컨텍스트
@AutoConfigureMockMvc //MockMVC 생성 및 자동 구성
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; //직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach //테스트 실행전 실행하는 메서드
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 블로그에 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception{

        //given
        final String url = "/api/articles";
        final String title = "오리테스트";
        final String content = "에슐리 먹고싶다.";
        final AddArticelRequest userRequest = new AddArticelRequest(title,content); //입력값 dto 에 담기
        // 객체를  JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        //설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        //then
        result.andExpect(status().isCreated());
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("삭제 테스트")
    @Test
    public void deleteTest() throws Exception {
        //given
        final String url = "/api/delete/{id}";
        final String title = "삭제테스트 타이틀";
        final String content = "삭제테스트 콘텐츠";

        Article savedArticle = blogRepository.save(Article.builder().title(title).content(content).build());

        //when
        mockMvc.perform(delete(url,savedArticle.getId())).andExpect(status().isOk());

        //then
        List<Article> articles = blogRepository.findAll();

        assertThat(articles).isEmpty();
    }

    @DisplayName("블로그 글 수정")
    @Test
    public void updateTest() throws Exception{

        final String oldTitle = "아배고파";
        final String oldContent = "넘배고파";

       Article savedArticle = blogRepository.save(Article.builder().title(oldTitle).content(oldContent).build());

        final String newUrl = "/api/articles/{id}";
        final String newTitle = "새로운";
        final String newContent = "수정사항";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle,newContent);

        ResultActions result = mockMvc.perform(put(newUrl,savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));


        result.andExpect(status().isOk());
        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);

    }
}