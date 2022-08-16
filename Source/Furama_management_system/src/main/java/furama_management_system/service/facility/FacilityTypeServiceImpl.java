package furama_management_system.service.facility;

import furama_management_system.entity.FacilityType;
import furama_management_system.repository.FacilityTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityTypeServiceImpl implements FacilityTypeService{
    @Autowired
    FacilityTypeRepo facilityTypeRepo;
    @Override
    public Page<FacilityType> findAll(Pageable pageable) {
        return facilityTypeRepo.findAll(pageable);
    }

    @Override
    public Optional<FacilityType> findById(int id) {
        return facilityTypeRepo.findById(id);
    }

    @Override
    public void save(FacilityType facilityType) {
        facilityTypeRepo.save(facilityType);
    }

    @Override
    public void deleteById(int id) {
        facilityTypeRepo.deleteById(id);
    }
}
