package dao;

import model.CustomerType;
import model.Position;

import java.util.List;

public interface PositionDAO {
    List<Position> selectAllPosition();
}
