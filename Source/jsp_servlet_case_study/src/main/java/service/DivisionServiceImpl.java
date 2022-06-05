package service;

import dao.DivisionDAO;
import dao.DivisionDAOImpl;
import model.Division;

import java.util.List;

public class DivisionServiceImpl implements DivisionService{
    DivisionDAO divisionDAO = new DivisionDAOImpl();
    @Override
    public List<Division> selectAllDivision() {
        return divisionDAO.selectAllDivision();
    }
}
