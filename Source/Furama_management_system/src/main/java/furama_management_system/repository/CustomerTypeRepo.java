package furama_management_system.repository;

import furama_management_system.entity.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepo extends JpaRepository<CustomerType,Integer> {
}
