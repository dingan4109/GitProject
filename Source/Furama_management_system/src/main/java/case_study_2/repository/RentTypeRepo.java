package case_study_2.repository;

import case_study_2.entity.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentTypeRepo extends JpaRepository<RentType,Integer> {
}
