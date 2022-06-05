package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class UserServiceImpl implements UserService{
    UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean insertUser(User user) {
        return userDAO.insertUser(user);
    }
}
