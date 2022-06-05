package service;

import model.RentType;

import java.util.List;

public interface RentTypeService {
    List<RentType> selectRentTypes();
    boolean insertRentType(RentType rentType);
}
