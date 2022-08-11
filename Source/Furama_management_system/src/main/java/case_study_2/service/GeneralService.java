package case_study_2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService<T> {
    Page<T> findAll(Pageable pageable);
    Optional<T> findById(int id);
    void save(T t);
    void deleteById(int id);
}
