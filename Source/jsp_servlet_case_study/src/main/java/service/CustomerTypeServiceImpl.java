package service;

import dao.CustomerTypeDAO;
import dao.CustomerTypeDAOImpl;
import model.CustomerType;

import java.util.List;

public class CustomerTypeServiceImpl implements CustomerTypeService {
    CustomerTypeDAO customerTypeDAO = new CustomerTypeDAOImpl();

    @Override
    public List<CustomerType> selectCustomerTypes() {
        return customerTypeDAO.selectCustomerTypes();
    }

    @Override
    public boolean insertCustomerType(CustomerType customerType) {
        return customerTypeDAO.insertCustomerType(customerType);
    }
}
