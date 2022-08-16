package furama_management_system.repository;

import furama_management_system.entity.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractDetailRepo extends JpaRepository<ContractDetail,Integer> {
}
