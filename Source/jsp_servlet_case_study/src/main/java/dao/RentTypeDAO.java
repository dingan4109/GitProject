package dao;

import model.CustomerType;
import model.RentType;

import java.util.List;

public interface RentTypeDAO {
    List<RentType> selectRentTypes();
    boolean insertRentType(RentType rentType);
}
