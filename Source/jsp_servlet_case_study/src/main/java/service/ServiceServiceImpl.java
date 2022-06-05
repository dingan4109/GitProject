package service;

import dao.ServiceDAO;
import dao.ServiceDAOImpl;
import model.Service;

import java.sql.SQLException;
import java.util.List;

public class ServiceServiceImpl implements ServiceService{
    ServiceDAO serviceDAO = new ServiceDAOImpl();
    @Override
    public List<Service> selectAllService() {
        return serviceDAO.selectAllService();
    }

    @Override
    public boolean insertService(Service service) throws SQLException {
        return serviceDAO.insertService(service);
    }
}
