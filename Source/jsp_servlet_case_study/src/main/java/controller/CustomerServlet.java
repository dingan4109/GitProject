package controller;

import model.Customer;
import model.CustomerType;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.CustomerTypeService;
import service.CustomerTypeServiceImpl;

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

@WebServlet(name = "AccountServlet", urlPatterns = "/customerServlet")
public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    CustomerTypeService customerTypeService = new CustomerTypeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "createCustomerType":
                createCustomerType(request, response);
                break;
            case "edit":
                editCustomer(request, response);
                break;
            case "delete":
                removeCustomer(request,response);
                break;
        }
    }

    private void removeCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        Customer customer = null;
        try {
            customer = customerService.selectCustomerById(customer_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = null;

        if (customer != null) {
            boolean check = false;
            try {
                check = customerService.deleteCustomer(customer_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(check) {
                response.sendRedirect("/customerServlet?currentPage=1");
            }else{
                request.setAttribute("mess","Fail to delete");
                dispatcher = request.getRequestDispatcher("customer/customerList.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Customer dose not exist");
            dispatcher = request.getRequestDispatcher("customer/customerList.jsp");
            dispatcher.forward(request, response);
        }


    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        String customer_type = request.getParameter("customer_type");
        int customer_type_id = 0;
        switch (customer_type) {
            case "VIP":
                customer_type_id = 1;
                break;
            case "Premium":
                customer_type_id = 2;
                break;
            case "Gold":
                customer_type_id = 3;
                break;
        }

        String customer_name = request.getParameter("name");
        String customer_birthday = request.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date bod = null;
        try {
            bod = formatter.parse(customer_birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int customer_gender = Integer.parseInt(request.getParameter("gender"));
        String customer_id_card = request.getParameter("idCard");
        String customer_phone = request.getParameter("phone");
        String customer_email = request.getParameter("email");
        String customer_address = request.getParameter("address");

        Customer customer = new Customer(customer_id,customer_type_id, customer_name, bod, customer_gender,
                customer_id_card,
                customer_phone, customer_email, customer_address);
        try {
            boolean check = customerService.updateCustomer(customer);
            if (check) {
                response.sendRedirect("/customerServlet?currentPage=1");
            } else {
                request.setAttribute("mess", "Fail to edit customer");
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/editCustomerForm.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException | ServletException | IOException throwables) {
            throwables.printStackTrace();
        }

    }

    private void createCustomerType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customer_type_name = request.getParameter("name");
        CustomerType customerType = new CustomerType(customer_type_name);

        customerTypeService.insertCustomerType(customerType);
        response.sendRedirect("/customerServlet?action=viewCustomerTypes");
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<CustomerType> customerTypes = customerTypeService.selectCustomerTypes();

        String customer_type = request.getParameter("customer_type");
        int customer_type_id = 0;
        for(int i = 0;i<customerTypes.size();i++) {
            if(customer_type.equals(customerTypes.get(i).getCustomerTypeName())){
                customer_type_id = customerTypes.get(i).getCustomerTypeId();
                break;
            }
        }

        String customer_name = request.getParameter("name");
        String customer_birthday = request.getParameter("birthday");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date bod = null;
        try {
            bod = formatter.parse(customer_birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int customer_gender = Integer.parseInt(request.getParameter("gender"));
        String customer_id_card = request.getParameter("idCard");
        String customer_phone = request.getParameter("phone");
        String customer_email = request.getParameter("email");
        String customer_address = request.getParameter("address");

       Customer customer = new Customer(customer_type_id, customer_name, bod, customer_gender, customer_id_card,
                customer_phone, customer_email, customer_address);
        try {
            boolean check = customerService.insertCustomer(customer);
            if (check) {
                response.sendRedirect("/customerServlet?currentPage=1");
            } else {
                request.setAttribute("mess", "Fail to create new customer");
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/createCustomerForm.jsp");
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
            case "create":
                createForm(request, response);
                break;
            case "createCustomerType":
                createCustomerTypeForm(request, response);
                break;
            case "edit":
                editCustomerForm(request, response);
                break;
            case "viewCustomerTypes":
                viewCustomerTypes(request, response);
                break;
            case "insertCustomerList":
                insertCustomerList(request,response);
            default:
                viewCustomerList(request, response);
                break;
        }

    }


    private void editCustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        Customer customer = null;
        List<CustomerType> customerTypes = customerTypeService.selectCustomerTypes();

        try {
            customer = customerService.selectCustomerById(customer_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = null;

        if (customer != null) {
            request.setAttribute("customerTypes", customerTypes);
            request.setAttribute("customer", customer);
            dispatcher = request.getRequestDispatcher("customer/editCustomerForm.jsp");
        } else {
            request.setAttribute("mess", "Customer dose not exist");
            dispatcher = request.getRequestDispatcher("customer/customerList.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void createCustomerTypeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/createCustomerTypeForm.jsp");
        dispatcher.forward(request, response);
    }

    private void viewCustomerTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerType> customerTypes = customerTypeService.selectCustomerTypes();

        request.setAttribute("customerTypes", customerTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/customerTypeList.jsp");
        dispatcher.forward(request, response);

    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerType> customerTypes = customerTypeService.selectCustomerTypes();

        request.setAttribute("customerTypes", customerTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/createCustomerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void viewCustomerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = null;
        List<CustomerType> customerTypes = null;
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));

        try {
            customerList = customerService.selectAllCustomer(currentPage);
            customerTypes = customerTypeService.selectCustomerTypes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int noOfCustomer = 0;
        try {
            noOfCustomer = customerService.countCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int noOfPage = noOfCustomer/5;

        if(noOfCustomer%5 > 0) {
            noOfPage ++;
        }

        request.setAttribute("customerList", customerList);
        request.setAttribute("customerTypes", customerTypes);
        request.setAttribute("noOfPage", noOfPage);
        request.setAttribute("currentPage", currentPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/customerList.jsp");
        dispatcher.forward(request, response);
    }


    private void getHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCustomerList(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> list = new ArrayList<>();
        Customer customer = null;
        String[] characters = {"abc","xyz","thjDF","ajghdja","Ugdfa","hasdy8724","jhsfg2","763276hdfg","7AG3"};
        int length = characters.length;
        String randomName = null;
        Date date = new Date();
        int type_id = 0;
        Random random = new Random();

        for(int i = 0; i < 5;i++) {
            int x = random.nextInt(length);
            randomName = characters[x];
            type_id = random.nextInt(3)+1;
            customer = new Customer(type_id,randomName,date,1,"abc","123","abc@haha.io","address");
            list.add(customer);
        }
        customerService.insertCustomerList(list);
    }

}
