package service;

import dao.EducationDegreeDAO;
import dao.EducationDegreeDAOImpl;
import model.EducationDegree;

import java.util.List;

public class EducationDegreeServiceImpl implements EducationDegreeService {
    EducationDegreeDAO educationDegreeDAO = new EducationDegreeDAOImpl();
    @Override
    public List<EducationDegree> selectAllEducationDegree() {
        return educationDegreeDAO.selectAllEducationDegree();
    }
}
