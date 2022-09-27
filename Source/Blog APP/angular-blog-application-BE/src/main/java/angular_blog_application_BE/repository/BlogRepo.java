package angular_blog_application_BE.repository;

import angular_blog_application_BE.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE blog SET like_number = :likeNumber WHERE id = :id", nativeQuery = true)
    void increaseLike(@Param("id") Long id, @Param("likeNumber") Long likeNumber);
}
