package angular_blog_application_BE.service;

import angular_blog_application_BE.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService extends GeneralService<Comment> {
    Page<Comment> findAllByBlog(Long blogId, Pageable pageable);
}
