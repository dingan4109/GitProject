package angular_blog_application_BE.service;

import angular_blog_application_BE.entity.Comment;
import angular_blog_application_BE.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepo commentRepo;

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepo.findAll(pageable);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepo.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public Page<Comment> findAllByBlog(Long blogId, Pageable pageable) {
        return commentRepo.findAllByBlog(blogId, pageable);
    }
}
