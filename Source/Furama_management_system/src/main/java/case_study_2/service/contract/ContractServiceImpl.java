package case_study_2.service.contract;

import case_study_2.entity.Contract;
import case_study_2.repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService{
    @Autowired
    ContractRepo contractRepo;

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return contractRepo.findAll(pageable);
    }

    @Override
    public Optional<Contract> findById(int id) {
        return contractRepo.findById(id);
    }

    @Override
    public void save(Contract contract) {
        contractRepo.save(contract);
    }

    @Override
    public void deleteById(int id) {
        contractRepo.deleteById(id);
    }
}
