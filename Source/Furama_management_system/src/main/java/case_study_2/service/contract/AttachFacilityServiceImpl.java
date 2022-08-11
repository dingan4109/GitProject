package case_study_2.service.contract;

import case_study_2.entity.AttachFacility;
import case_study_2.repository.AttachFacilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttachFacilityServiceImpl implements AttachFacilityService{
    @Autowired
    AttachFacilityRepo attachFacilityRepo;

    @Override
    public Page<AttachFacility> findAll(Pageable pageable) {
        return attachFacilityRepo.findAll(pageable);
    }

    @Override
    public Optional<AttachFacility> findById(int id) {
        return attachFacilityRepo.findById(id);
    }

    @Override
    public void save(AttachFacility attachFacility) {
        attachFacilityRepo.save(attachFacility);
    }

    @Override
    public void deleteById(int id) {
        attachFacilityRepo.deleteById(id);
    }
}
