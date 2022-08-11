package blog_app.repository;

import blog_app.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepo extends JpaRepository<Blog,Integer> {
    Page<Blog> findAllByOrderById(Pageable pageable);
    Page<Blog> findAllByCategory_CategoryId(int id,Pageable pageable);
    Page<Blog> findAllByTitleContains(String title, Pageable pageable);
}
