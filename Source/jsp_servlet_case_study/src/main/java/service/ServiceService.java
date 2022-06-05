package service;

import model.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceService {
    List<Service> selectAllService();
    boolean insertService(Service service) throws SQLException;
}
