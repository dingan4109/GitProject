package furama_management_system.repository;

import furama_management_system.entity.EducationDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationDegreeRepo extends JpaRepository<EducationDegree,Integer> {
}
