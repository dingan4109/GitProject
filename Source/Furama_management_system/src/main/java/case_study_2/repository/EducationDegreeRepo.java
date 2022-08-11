package case_study_2.repository;

import case_study_2.entity.EducationDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationDegreeRepo extends JpaRepository<EducationDegree,Integer> {
}
