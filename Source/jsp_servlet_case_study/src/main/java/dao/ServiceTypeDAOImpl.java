package dao;

import model.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeDAOImpl implements ServiceTypeDAO{
    private static final String INSERT_SERVICE_TYPE = "INSERT INTO service_type(service_type_name) " +
            "VALUES" +
            "(?)";
    private static final String SELECT_SERVICE_TYPES = "SELECT * FROM service_type";
    @Override
    public List<ServiceType> selectServiceTypes() {
        Connection connection = ConnectionObject.getConnection();
        List<ServiceType> serviceTypes = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICE_TYPES);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int service_type_id = rs.getInt(1);
                String service_type_name = rs.getString(2);

                ServiceType serviceType = new ServiceType(service_type_id,service_type_name);
                serviceTypes.add(serviceType);
            }
            connection.close();
        }catch (SQLException e) {
            e.getStackTrace();
        }
        return serviceTypes;
    }

    @Override
    public boolean insertServiceType(ServiceType serviceType) {
        Connection connection = ConnectionObject.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_TYPE);
            preparedStatement.setString(1,serviceType.getServiceTypeName());

            boolean check = preparedStatement.executeUpdate() > 0;
            connection.close();

            if(check) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
