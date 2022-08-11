package blog_app.service;

import blog_app.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category findById(int id);
    void save(Category category);
    void deleteById(int id);
}
