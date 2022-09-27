package angular_blog_application_BE.service;

import angular_blog_application_BE.entity.Blog;

public interface BlogService extends GeneralService<Blog> {
    void increaseLike(Long id, Long likeNumber);
}
