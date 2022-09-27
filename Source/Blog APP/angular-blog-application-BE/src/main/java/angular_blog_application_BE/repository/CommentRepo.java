package angular_blog_application_BE.repository;

import angular_blog_application_BE.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment  WHERE blog_id = :blogId", nativeQuery = true)
    Page<Comment> findAllByBlog(@Param("blogId") Long blogId, Pageable pageable);
}
