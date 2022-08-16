package furama_management_system.service.employee;

import furama_management_system.entity.Division;
import furama_management_system.repository.DivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DivisionServiceImpl implements DivisionService{
    @Autowired
    DivisionRepo divisionRepo;

    @Override
    public Page<Division> findAll(Pageable pageable) {
        return divisionRepo.findAll(pageable);
    }

    @Override
    public Optional<Division> findById(int id) {
        return divisionRepo.findById(id);
    }

    @Override
    public void save(Division division) {
        divisionRepo.save(division);
    }

    @Override
    public void deleteById(int id) {
        divisionRepo.deleteById(id);
    }
}
