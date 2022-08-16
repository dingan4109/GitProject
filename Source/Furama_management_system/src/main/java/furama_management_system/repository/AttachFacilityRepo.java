package furama_management_system.repository;

import furama_management_system.entity.AttachFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFacilityRepo extends JpaRepository<AttachFacility,Integer> {
}
