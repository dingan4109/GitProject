package blog_app.service;

import blog_app.entity.Blog;
import blog_app.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService {
//    Pageable fistPage = PageRequest.of(0,2, Sort.by("id"));
    @Autowired
    BlogRepo blogRepo;
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepo.findAllByOrderById(pageable);
    }

    @Override
    public Blog findById(int id) {
        return blogRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void deleteById(int id) {
        blogRepo.deleteById(id);
    }

    @Override
    public Page<Blog> findAllByCategory(int id, Pageable pageable) {
        return blogRepo.findAllByCategory_CategoryId(id,pageable);
    }

    @Override
    public Page<Blog> search(String title,Pageable pageable) {
        return blogRepo.findAllByTitleContains(title,pageable);
    }
}
