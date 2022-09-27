package blog_app.service;

import blog_app.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    Blog findById(int id);
    void save(Blog blog);
    void deleteById(int id);
    Page<Blog> findAllByCategory(int id,Pageable pageable);
    Page<Blog> search(String title,Pageable pageable);
}
