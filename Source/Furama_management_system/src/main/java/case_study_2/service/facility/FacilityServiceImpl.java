package case_study_2.service.facility;

import case_study_2.entity.Facility;
import case_study_2.repository.FacilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    FacilityRepo facilityRepo;

    @Override
    public Page<Facility> findAll(Pageable pageable) {
        return facilityRepo.findAll(pageable);
    }

    @Override
    public Optional<Facility> findById(int id) {
        return facilityRepo.findById(id);
    }

    @Override
    public void save(Facility facility) {
        facilityRepo.save(facility);
    }

    @Override
    public void deleteById(int id) {
        facilityRepo.deleteById(id);
    }
}
