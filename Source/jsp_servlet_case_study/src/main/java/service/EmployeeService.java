package service;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    List<Employee> selectAllEmployee(int currentPage) throws SQLException;
    Employee selectEmployeeById(int id);
    boolean insertEmployee(Employee employee) throws SQLException;
    boolean updateEmployee(int id) throws SQLException;
    boolean deleteEmployee(int id) throws SQLException;

    int countEmployees() throws SQLException;
    void insertEmployeeList(List<Employee> list);
}
