package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> selectAllEmployee() throws SQLException;
    Employee selectEmployeeById(int id);
    void insertEmployee(Employee employee) throws SQLException;
    boolean updateEmployee(int id) throws SQLException;
    boolean deleteEmployee(int id) throws SQLException;

}
