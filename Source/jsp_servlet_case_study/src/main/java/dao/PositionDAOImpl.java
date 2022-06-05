package dao;

import model.CustomerType;
import model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAOImpl implements PositionDAO {
    private static final String SELECT_ALL_POSITION = "SELECT * FROM position";
    @Override
    public List<Position> selectAllPosition() {
        Connection connection = ConnectionObject.getConnection();
        List<Position> positionList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POSITION);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int positionId = rs.getInt(1);
                String positionName = rs.getString(2);

                Position position = new Position(positionId,positionName);
                positionList.add(position);
            }
            connection.close();
        }catch (SQLException e) {
            e.getStackTrace();
        }
        return positionList;
    }
}
