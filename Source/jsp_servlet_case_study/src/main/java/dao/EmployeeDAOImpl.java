package dao;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    private final static String SELECT_ALL_EMPLOYEE = "SELECT * FROM employee";

    @Override
    public List<Employee> selectAllEmployee() throws SQLException {
        //connect to database
        Connection connection = ConnectionObject.getConnection();

        List<Employee> employeeList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()) {
            int employeeId = rs.getInt("employee_id");
            String employeeName = rs.getString("employee_name");
            Date employeeBirthday = rs.getDate("employee_birthday");
            String employeeIdCard = rs.getString("employee_id_card");
            double employeeSalary = rs.getDouble("employee_salary");
            String employeePhone = rs.getString("employee_phone");
            String employeeEmail = rs.getString("employee_email");
            String employeeAddress = rs.getString("employee_address");

            Employee employee = new Employee(employeeId,employeeName,employeeBirthday,employeeIdCard,employeeSalary,
                    employeePhone,employeeEmail,employeeAddress);

            employeeList.add(employee);
        }
        connection.close();

        return employeeList;
    }

    @Override
    public Employee selectEmployeeById(int id) {
        return null;
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {

    }

    @Override
    public boolean updateEmployee(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) throws SQLException {
        return false;
    }
}
