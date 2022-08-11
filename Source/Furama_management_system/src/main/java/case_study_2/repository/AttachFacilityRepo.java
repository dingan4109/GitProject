package case_study_2.repository;

import case_study_2.entity.AttachFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFacilityRepo extends JpaRepository<AttachFacility,Integer> {
}
