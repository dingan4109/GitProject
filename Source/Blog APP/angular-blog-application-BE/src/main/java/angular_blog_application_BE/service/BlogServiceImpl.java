package angular_blog_application_BE.service;

import angular_blog_application_BE.entity.Blog;
import angular_blog_application_BE.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    BlogRepo blogRepo;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepo.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void deleteById(Long id) {
        blogRepo.deleteById(id);
    }

    @Override
    public void increaseLike(Long id, Long likeNumber) {
        blogRepo.increaseLike(id, likeNumber);
    }
}
