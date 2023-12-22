package me.thirdDuck.threeDuckBlog.blogDevelop.repository;

import me.thirdDuck.threeDuckBlog.blogDevelop.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {

}
