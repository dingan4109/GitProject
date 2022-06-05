package dao;

import model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    private static final String INSERT_SERVICE = "INSERT INTO service(service_name,service_area,service_cost," +
            "service_max_people,rent_type_id,service_type_id,standard_room,description_other_convenience,pool_area," +
            "number_of_floors) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_SERVICE = "SELECT * FROM service";
    @Override
    public List<Service> selectAllService() {
        Connection connection = ConnectionObject.getConnection();
        List<Service> serviceList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SERVICE);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int service_id = rs.getInt(1);
                String service_name = rs.getString(2);
                int service_area = rs.getInt(3);
                double service_cost = rs.getDouble(4);
                int service_max_people = rs.getInt(5);
                int rent_type_id = rs.getInt(6);
                int service_type_id = rs.getInt(7);
                String standard_room = rs.getString(8);
                String description_other_convenience = rs.getString(9);
                double pool_area = rs.getDouble(10);
                int number_of_floors = rs.getInt(11);

                Service service = new Service(service_id,service_name,service_area,service_cost,service_max_people,
                        rent_type_id,service_type_id,standard_room,description_other_convenience,pool_area,number_of_floors);
                serviceList.add(service);
            }
            connection.close();
        }catch (SQLException e) {
            e.getStackTrace();
        }
        return serviceList;
    }

    @Override
    public boolean insertService(Service service) throws SQLException{
        Connection connection = ConnectionObject.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE);

        preparedStatement.setString(1,service.getServiceName());
        preparedStatement.setInt(2,service.getServiceArea());
        preparedStatement.setDouble(3,service.getServiceCost());
        preparedStatement.setInt(4,service.getServiceMaxPeople());
        preparedStatement.setInt(5,service.getRentTypeId());
        preparedStatement.setInt(6,service.getServiceTypeId());
        preparedStatement.setString(7,service.getStandardRoom());
        preparedStatement.setString(8,service.getDescriptionOtherConvenience());
        preparedStatement.setDouble(9,service.getPoolArea());
        preparedStatement.setInt(10,service.getNumberOfFloors());

        boolean check = preparedStatement.executeUpdate() > 0;
        connection.close();

        if(check) return true;
        else return false;
    }
}
