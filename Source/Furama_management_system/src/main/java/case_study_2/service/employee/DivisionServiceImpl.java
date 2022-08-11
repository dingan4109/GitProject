package case_study_2.service.employee;

import case_study_2.entity.Division;
import case_study_2.repository.DivisionRepo;
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
