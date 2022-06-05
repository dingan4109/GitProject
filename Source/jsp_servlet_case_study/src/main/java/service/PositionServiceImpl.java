package service;

import dao.PositionDAO;
import dao.PositionDAOImpl;
import model.Position;

import java.util.List;

public class PositionServiceImpl implements PositionService{
    PositionDAO positionDAO = new PositionDAOImpl();
    @Override
    public List<Position> selectAllPosition() {
        return positionDAO.selectAllPosition();
    }
}
