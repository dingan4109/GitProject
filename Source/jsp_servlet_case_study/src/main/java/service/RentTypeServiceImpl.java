package service;

import dao.RentTypeDAO;
import dao.RentTypeDAOImpl;
import model.RentType;

import java.util.List;

public class RentTypeServiceImpl implements RentTypeService{
    RentTypeDAO rentTypeDAO = new RentTypeDAOImpl();
    @Override
    public List<RentType> selectRentTypes() {
        return rentTypeDAO.selectRentTypes();
    }

    @Override
    public boolean insertRentType(RentType rentType) {
        return rentTypeDAO.insertRentType(rentType);
    }
}
