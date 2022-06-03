package dao;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{
    private final static String SELECT_ALL_CUSTOMER = "SELECT * FROM customer LIMIT ?,?";
    private final static String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
    private final static String INSERT_CUSTOMER = "INSERT INTO customer(customer_type_id,customer_name," +
            "customer_birthday," +
            "customer_gender,customer_id_card,customer_phone,customer_email,customer_address) VALUES(?,?,?,?,?,?,?,?)";
    private final static String UPDATE_CUSTOMER = "UPDATE customer SET customer_type_id = ?,customer_name = ?," +
            "customer_birthday =?,customer_gender=?,customer_id_card=?,customer_phone=?,customer_email=?," +
            "customer_address=?" +
            " WHERE " +
            "customer_id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer_id = ?";
    private static final String COUNT_CUSTOMER = "SELECT COUNT(customer_id) FROM customer";

    @Override
    public List<Customer> selectAllCustomer(int currentPage) throws SQLException {
        //connect to database
        Connection connection = ConnectionObject.getConnection();

        int recordsPerPage = 5;
        int startIndex = currentPage * recordsPerPage - recordsPerPage;

        List<Customer> customerList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);

        preparedStatement.setInt(1,startIndex);
        preparedStatement.setInt(2,recordsPerPage);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
           Customer customer = getCustomer(rs);
            customerList.add(customer);
        }
        connection.close();
        return customerList;
    }

    @Override
    public Customer selectCustomerById(int id) throws SQLException {
        Connection connection = ConnectionObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
        preparedStatement.setInt(1,id);

        ResultSet rs = preparedStatement.executeQuery();
        Customer customer = null;

        if(rs.next()) {
            customer = getCustomer(rs);
        }
        connection.close();
        return customer;
    }

    private Customer getCustomer(ResultSet rs) throws SQLException {
        int customerId = rs.getInt("customer_id");
        int customerTypeId = rs.getInt("customer_type_id");
        String customerName = rs.getString("customer_name");
        Date customerBirthday = rs.getDate("customer_birthday");
        int customerGender = rs.getInt("customer_gender");
        String customerIdCard = rs.getString("customer_id_card");
        String customerPhone = rs.getString("customer_phone");
        String customerEmail = rs.getString("customer_email");
        String customerAddress = rs.getString("customer_address");

        Customer customer = new Customer(customerId,customerTypeId,customerName,customerBirthday,customerGender,
                customerIdCard,
                customerPhone,customerEmail,customerAddress);

        return customer;
    }

    @Override
    public boolean insertCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectionObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
        java.sql.Date sqlBod = new java.sql.Date(customer.getCustomerBirthday().getTime());

        preparedStatement.setInt(1,customer.getCustomerTypeId());
        preparedStatement.setString(2,customer.getCustomerName());
        preparedStatement.setDate(3, sqlBod);
        preparedStatement.setInt(4,customer.getCustomerGender());
        preparedStatement.setString(5,customer.getCustomerIdCard());
        preparedStatement.setString(6,customer.getCustomerPhone());
        preparedStatement.setString(7,customer.getCustomerEmail());
        preparedStatement.setString(8,customer.getCustomerAddress());

        boolean check = preparedStatement.executeUpdate()>0;
        connection.close();

        return check;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectionObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);

        java.sql.Date sqlBod = new java.sql.Date(customer.getCustomerBirthday().getTime());

        preparedStatement.setInt(1,customer.getCustomerTypeId());
        preparedStatement.setString(2,customer.getCustomerName());
        preparedStatement.setDate(3, sqlBod);
        preparedStatement.setInt(4,customer.getCustomerGender());
        preparedStatement.setString(5,customer.getCustomerIdCard());
        preparedStatement.setString(6,customer.getCustomerPhone());
        preparedStatement.setString(7,customer.getCustomerEmail());
        preparedStatement.setString(8,customer.getCustomerAddress());
        preparedStatement.setInt(9,customer.getCustomerId());

        boolean check = preparedStatement.executeUpdate() > 0;
        connection.close();

        return check;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        Connection connection = ConnectionObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);

        preparedStatement.setInt(1,id);
        boolean check = preparedStatement.executeUpdate() > 0;
        connection.close();

        return check;
    }

    @Override
    public void insertCustomerList(List<Customer> list) {
        for(int i = 0;i<list.size();i++) {
            try {
                boolean check = insertCustomer(list.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int countCustomers() throws SQLException {
        Connection connection = ConnectionObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_CUSTOMER);

        ResultSet rs = preparedStatement.executeQuery();
        int noOfCustomer = 0;

        if(rs.next())
        noOfCustomer = rs.getInt(1);

        return noOfCustomer;
    }


}
