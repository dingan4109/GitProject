package service;

import model.CustomerType;

import java.util.List;

public interface CustomerTypeService {
    List<CustomerType> selectCustomerTypes();
    boolean insertCustomerType(CustomerType customerType);
}
