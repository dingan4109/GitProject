package dao;

import model.CustomerType;

import java.util.List;

public interface CustomerTypeDAO {
    List<CustomerType> selectCustomerTypes();
    boolean insertCustomerType(CustomerType customerType);
}
