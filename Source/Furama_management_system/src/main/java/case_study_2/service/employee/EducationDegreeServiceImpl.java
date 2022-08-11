package case_study_2.service.employee;

import case_study_2.entity.EducationDegree;
import case_study_2.repository.EducationDegreeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducationDegreeServiceImpl implements EducationDegreeService{
    @Autowired
    EducationDegreeRepo educationDegreeRepo;

    @Override
    public Page<EducationDegree> findAll(Pageable pageable) {
        return educationDegreeRepo.findAll(pageable);
    }

    @Override
    public Optional<EducationDegree> findById(int id) {
        return educationDegreeRepo.findById(id);
    }

    @Override
    public void save(EducationDegree educationDegree) {
        educationDegreeRepo.save(educationDegree);
    }

    @Override
    public void deleteById(int id) {
        educationDegreeRepo.deleteById(id);
    }
}
