package angular_blog_application_BE.service;

import angular_blog_application_BE.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService extends GeneralService<Blog> {
    void increaseLike(Long id, Long likeNumber);
    Page<Blog> findBlogByCategory(Long categoryId, Pageable pageable);
}
