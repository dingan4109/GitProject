package service;

import model.ServiceType;

import java.util.List;

public interface ServiceTypeService {
    List<ServiceType> selectServiceTypes();
    boolean insertServiceType(ServiceType serviceType);
}
