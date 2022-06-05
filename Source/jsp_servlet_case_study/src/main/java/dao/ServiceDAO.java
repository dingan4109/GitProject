package dao;


import model.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO {
    List<Service> selectAllService();
    boolean insertService(Service service) throws SQLException;
}
