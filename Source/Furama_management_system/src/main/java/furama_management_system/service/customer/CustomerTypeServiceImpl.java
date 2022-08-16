package furama_management_system.service.customer;

import furama_management_system.entity.CustomerType;
import furama_management_system.repository.CustomerTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService{
    @Autowired
    CustomerTypeRepo customerTypeRepo;

    @Override
    public Page<CustomerType> findAll(Pageable pageable) {
        return customerTypeRepo.findAll(pageable);
    }

    @Override
    public Optional<CustomerType> findById(int id) {
        return customerTypeRepo.findById(id);
    }

    @Override
    public void save(CustomerType customerType) {
        customerTypeRepo.save(customerType);
    }

    @Override
    public void deleteById(int id) {
        customerTypeRepo.deleteById(id);
    }
}
