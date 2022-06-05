package controller;

import model.*;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employeeServlet")
public class EmployeeServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    PositionService positionService = new PositionServiceImpl();
    EducationDegreeService educationDegreeService = new EducationDegreeServiceImpl();
    DivisionService divisionService = new DivisionServiceImpl();
    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createEmployee":
                createEmployee(request, response);
                break;
            case "user":
                user(request, response);
                break;
        }

    }

    private void user(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        User user = new User(name,pass);
        userService.insertUser(user);
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Position> positionList = positionService.selectAllPosition();
        List<EducationDegree> educationDegreeList = educationDegreeService.selectAllEducationDegree();
        List<Division> divisionList = divisionService.selectAllDivision();

        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date bod = null;
        try {
            bod = formatter.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String idCard = request.getParameter("idCard");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        int positionId = 0;
        for (int i = 0; i < positionList.size(); i++) {
            if (position.equals(positionList.get(i).getPositionName())) {
                positionId = positionList.get(i).getPositionId();
                break;
            }
        }
        String educationDegree = request.getParameter("educationDegree");
        int educationDegreeId = 0;
        for (int i = 0; i < educationDegreeList.size(); i++) {
            if (educationDegree.equals(educationDegreeList.get(i).getEducationDegreeName())) {
                educationDegreeId = educationDegreeList.get(i).getEducationDegreeId();
                break;
            }
        }
        String division = request.getParameter("division");
        int divisionId = 0;
        for (int i = 0; i < divisionList.size(); i++) {
            if (division.equals(divisionList.get(i).getDivisionName())) {
                divisionId = divisionList.get(i).getDivisionId();
                break;
            }
        }
        String username = request.getParameter("username");
        String password = "123";
        User user = new User(username,password);
        userService.insertUser(user);

        Employee employee = new Employee(name, bod, idCard, salary,
                phone, email, address, positionId, educationDegreeId, divisionId, username);
        try {
            boolean check = employeeService.insertEmployee(employee);
            if (check) {
                response.sendRedirect("/employeeServlet?currentPage=1");
            } else {
                request.setAttribute("mess", "Fail to create new customer");
                RequestDispatcher dispatcher = request.getRequestDispatcher("employee/createEmployeeForm.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException | ServletException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "createEmployee":
                createEmployeeForm(request, response);
                break;
            case "insertList":
                insertList(request, response);
                break;
            case "user":
                viewUser(request, response);
                break;
            default:
                viewEmployeeList(request, response);
                break;
        }
    }

    private void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/createUser.jsp");
        dispatcher.forward(request, response);
    }

    private void insertList(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        String[] characters = {"Nguyễn Văn A", "Trần Văn B", "Lê Lợi", "Ngô QUyền", "Hai Bà Trưng", "HUyền Trân Công Chúa",
                "Đinh Bộ Lĩnh",
                "Mị Châu",
                "Trọng Thủy", "Vua Hùng 18", "Võ Thị Sáu", "Bùi Thị Xuân"};
        int length = characters.length;
        String randomName = null;
        Date date = new Date();
        int position_id = 0;
        int degree_id = 0;
        int division_id = 0;
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(length);
            randomName = characters[x];
            position_id = random.nextInt(2) + 1;
            degree_id = random.nextInt(4) + 1;
            division_id = random.nextInt(4) + 1;
            employee = new Employee(randomName, date, "1257482", 10000, "090333444", "abc@haha.io", "address", position_id,
                    degree_id, division_id, "abc");
            list.add(employee);
        }
        employeeService.insertEmployeeList(list);
    }

    private void createEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Position> positionList = positionService.selectAllPosition();
        List<EducationDegree> educationDegreeList = educationDegreeService.selectAllEducationDegree();
        List<Division> divisionList = divisionService.selectAllDivision();

        request.setAttribute("positionList", positionList);
        request.setAttribute("educationDegreeList", educationDegreeList);
        request.setAttribute("divisionList", divisionList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/createEmployeeForm.jsp");
        dispatcher.forward(request, response);
    }

    private void viewEmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        List<Employee> employeeList = null;
        List<Position> positionList = positionService.selectAllPosition();
        List<EducationDegree> educationDegreeList = educationDegreeService.selectAllEducationDegree();
        List<Division> divisionList = divisionService.selectAllDivision();

        try {
            employeeList = employeeService.selectAllEmployee(currentPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int noOfEmployees = 0;
        try {
            noOfEmployees = employeeService.countEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int noOfPages = noOfEmployees / 5;
        if (noOfEmployees % 5 > 0) {
            noOfPages++;
        }

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("positionList", positionList);
        request.setAttribute("educationDegreeList", educationDegreeList);
        request.setAttribute("divisionList", divisionList);
        request.setAttribute("currentPage", currentPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeList.jsp");
        dispatcher.forward(request, response);

    }
}
