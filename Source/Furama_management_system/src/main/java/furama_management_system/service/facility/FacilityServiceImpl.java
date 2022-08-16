package furama_management_system.service.facility;

import furama_management_system.entity.Facility;
import furama_management_system.repository.FacilityRepo;
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
