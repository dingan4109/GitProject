package service;

import dao.ServiceTypeDAO;
import dao.ServiceTypeDAOImpl;
import model.ServiceType;

import java.util.List;

public class ServiceTypeServiceImpl implements ServiceTypeService{
    ServiceTypeDAO serviceTypeDAO = new ServiceTypeDAOImpl();
    @Override
    public List<ServiceType> selectServiceTypes() {
        return serviceTypeDAO.selectServiceTypes();
    }

    @Override
    public boolean insertServiceType(ServiceType serviceType) {
        return serviceTypeDAO.insertServiceType(serviceType);
    }
}
