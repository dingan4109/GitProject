package dao;

import model.ServiceType;

import java.util.List;

public interface ServiceTypeDAO {
    List<ServiceType> selectServiceTypes();
    boolean insertServiceType(ServiceType serviceType);
}
