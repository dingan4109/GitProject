package furama_management_system.repository;

import furama_management_system.entity.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityTypeRepo extends JpaRepository<FacilityType,Integer> {
}
