package controller;

import model.CustomerType;
import model.RentType;
import model.Service;
import model.ServiceType;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServiceServlet",urlPatterns = "/serviceServlet")
public class ServiceServlet extends HttpServlet {
    RentTypeService rentTypeService = new RentTypeServiceImpl();
    ServiceTypeService serviceTypeService = new ServiceTypeServiceImpl();
    ServiceService serviceService = new ServiceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "createService":
                createService(request,response);
                break;
        }

    }

    private void createService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<RentType> rentTypes = rentTypeService.selectRentTypes();
        List<ServiceType> serviceTypes = serviceTypeService.selectServiceTypes();

        String serviceName = request.getParameter("name");
        int serviceArea = Integer.parseInt(request.getParameter("area"));
        double serviceCost = Double.parseDouble(request.getParameter("cost"));
        int serviceMaxPeople = Integer.parseInt(request.getParameter("max_people"));
        String rentType = request.getParameter("rent_type");
        int rentTypeId = 0;
        for(int i = 0;i<rentTypes.size();i++) {
            if(rentType.equals(rentTypes.get(i).getRentTypeName()))
                rentTypeId = rentTypes.get(i).getRentTypeId();
            break;
        }
        String serviceType = request.getParameter("service_type");
        int serviceTypeId = 0;
        for(int i = 0;i<serviceTypes.size();i++) {
            if(serviceType.equals(serviceTypes.get(i).getServiceTypeName()))
                serviceTypeId = serviceTypes.get(i).getServiceTypeId();
        }
        String standardRoom = request.getParameter("standard_room");
        String descriptionOtherConvenience = request.getParameter("description");
        double poolArea = Double.parseDouble(request.getParameter("pool_area"));
        int numberOfFloors = Integer.parseInt(request.getParameter("floors"));

        Service service = new Service(serviceName,serviceArea,serviceCost,serviceMaxPeople,rentTypeId,serviceTypeId,
                standardRoom,descriptionOtherConvenience,poolArea,numberOfFloors);

        boolean check = false;
        try {
            check = serviceService.insertService(service);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(check) {
            response.sendRedirect("/serviceServlet");
        }else {
            request.setAttribute("mess","Failed to create new service");
            RequestDispatcher dispatcher = request.getRequestDispatcher("service/createServiceForm.jsp");
            dispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null) {
            action = "";
        }
        switch (action) {
            case "createService":
                createServiceForm(request,response);
                break;
            case "viewRentTypes":
                viewRentType(request,response);
                break;
            case "viewServiceTypes":
                viewServiceType(request,response);
                break;
            default:
                viewServiceList(request,response);
                break;
        }
    }

    private void createServiceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RentType> rentTypes = rentTypeService.selectRentTypes();
        List<ServiceType> serviceTypes = serviceTypeService.selectServiceTypes();

        request.setAttribute("rentTypes",rentTypes);
        request.setAttribute("serviceTypes",serviceTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("service/createServiceForm.jsp");
        dispatcher.forward(request,response);
    }

    private void viewServiceList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RentType> rentTypes = rentTypeService.selectRentTypes();
        List<ServiceType> serviceTypes = serviceTypeService.selectServiceTypes();
        List<Service> serviceList = serviceService.selectAllService();

        request.setAttribute("rentTypes",rentTypes);
        request.setAttribute("serviceTypes",serviceTypes);
        request.setAttribute("serviceList",serviceList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("service/serviceList.jsp");
        dispatcher.forward(request,response);

    }

    private void viewServiceType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ServiceType> serviceTypes = serviceTypeService.selectServiceTypes();

        request.setAttribute("serviceTypes",serviceTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("service/serviceTypeList.jsp");
        dispatcher.forward(request,response);
    }

    private void viewRentType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RentType> rentTypes = rentTypeService.selectRentTypes();

        request.setAttribute("rentTypes", rentTypes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("service/rentTypeList.jsp");
        dispatcher.forward(request, response);
    }
}
