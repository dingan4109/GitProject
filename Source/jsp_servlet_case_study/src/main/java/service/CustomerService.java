package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<Customer> selectAllCustomer(int currentPage) throws SQLException;
    Customer selectCustomerById(int id) throws SQLException;
    boolean insertCustomer(Customer customer) throws SQLException;
    boolean updateCustomer(Customer customer) throws SQLException;
    boolean deleteCustomer(int id) throws SQLException;

    void insertCustomerList(List<Customer> list);
    int countCustomers() throws SQLException;
}
