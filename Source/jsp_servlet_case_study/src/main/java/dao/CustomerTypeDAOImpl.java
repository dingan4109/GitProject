package dao;

import model.CustomerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeDAOImpl implements CustomerTypeDAO {
    private static final String INSERT_CUSTOMER_TYPE = "INSERT INTO customer_type(customer_type_name) VALUES(?)";
    private static final String SELECT_CUSTOMER_TYPES = "SELECT * FROM customer_type";

    @Override
    public List<CustomerType> selectCustomerTypes() {
        Connection connection = ConnectionObject.getConnection();
        List<CustomerType> customerTypes = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_TYPES);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int customer_type_id = rs.getInt(1);
                String customer_type_name = rs.getString(2);

                CustomerType customerType = new CustomerType(customer_type_id,customer_type_name);
                customerTypes.add(customerType);
            }
            connection.close();
        }catch (SQLException e) {
            e.getStackTrace();
        }
        return customerTypes;
    }

    @Override
    public boolean insertCustomerType(CustomerType customerType) {

        Connection connection = ConnectionObject.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_TYPE);
            preparedStatement.setString(1,customerType.getCustomerTypeName());

            boolean check = preparedStatement.executeUpdate() > 0;
            connection.close();

            if(check) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
