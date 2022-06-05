package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{
    private static final String INSERT_USER = "INSERT INTO `user` VALUES(?,?)";
    @Override
    public boolean insertUser(User user) {
        Connection connection = ConnectionObject.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());

            boolean check = preparedStatement.executeUpdate() > 0;
            connection.close();

            if(check) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
