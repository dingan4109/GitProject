package furama_management_system.repository;

import furama_management_system.entity.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentTypeRepo extends JpaRepository<RentType,Integer> {
}
