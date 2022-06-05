package dao;

import model.RentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentTypeDAOImpl implements RentTypeDAO {
    private static final String INSERT_RENT_TYPE = "INSERT INTO rent_type(rent_type_name,rent_type_cost) VALUES" +
            "(?,?)";
    private static final String SELECT_RENT_TYPES = "SELECT * FROM rent_type";

    @Override
    public List<RentType> selectRentTypes() {
        Connection connection = ConnectionObject.getConnection();
        List<RentType> rentTypes = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RENT_TYPES);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int rent_type_id = rs.getInt(1);
                String rent_type_name = rs.getString(2);
                double rent_type_cost = rs.getDouble(3);

                RentType rentType = new RentType(rent_type_id,rent_type_name,rent_type_cost);
                rentTypes.add(rentType);
            }
            connection.close();
        }catch (SQLException e) {
            e.getStackTrace();
        }
        return rentTypes;
    }

    @Override
    public boolean insertRentType(RentType rentType) {
        Connection connection = ConnectionObject.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RENT_TYPE);
            preparedStatement.setString(1,rentType.getRentTypeName());
            preparedStatement.setDouble(2,rentType.getRentTypeCost());

            boolean check = preparedStatement.executeUpdate() > 0;
            connection.close();

            if(check) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
