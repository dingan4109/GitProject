package angular_blog_application_BE.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService<T> {
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(Long id);
    void save(T t);
    void deleteById(Long id);
}
