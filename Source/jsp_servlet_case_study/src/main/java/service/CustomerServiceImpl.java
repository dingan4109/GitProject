package service;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<Customer> selectAllCustomer(int currentPage) throws SQLException {
        List<Customer> customerList = customerDAO.selectAllCustomer(currentPage);

        return customerList;
    }

    @Override
    public Customer selectCustomerById(int id) throws SQLException {
        return customerDAO.selectCustomerById(id);
    }

    @Override
    public boolean insertCustomer(Customer customer) throws SQLException {
        return customerDAO.insertCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        return customerDAO.deleteCustomer(id);
    }

    @Override
    public void insertCustomerList(List<Customer> list) {
        customerDAO.insertCustomerList(list);
    }

    @Override
    public int countCustomers() throws SQLException {
        return customerDAO.countCustomers();
    }


}
