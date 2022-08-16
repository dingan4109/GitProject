package furama_management_system.controller;

import furama_management_system.entity.*;
import furama_management_system.service.contract.AttachFacilityService;
import furama_management_system.service.contract.ContractDetailService;
import furama_management_system.service.contract.ContractService;
import furama_management_system.service.customer.CustomerService;
import furama_management_system.service.employee.EmployeeService;
import furama_management_system.service.facility.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("contracts")
public class ContractController {
    @Autowired
    ContractService contractService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    FacilityService facilityService;
    @Autowired
    ContractDetailService contractDetailService;
    @Autowired
    AttachFacilityService attachFacilityService;

    @ModelAttribute("attachFacilities")
    public Page<AttachFacility> getAttachFacilities(Pageable pageable) {
        Page<AttachFacility> attachFacilities = attachFacilityService.findAll(pageable);
        return attachFacilities;
    }
    @ModelAttribute("employees")
    public Page<Employee> getEmployees(Pageable pageable) {
        Page<Employee> employees = employeeService.findAll(pageable);
        return employees;
    }
    @ModelAttribute("customers")
    public Page<Customer> getCustomers(Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        return customers;
    }
    @ModelAttribute("facilities")
    public Page<Facility> getFacilities(Pageable pageable) {
        Page<Facility> facilities = facilityService.findAll(pageable);
        return facilities;
    }

    @ModelAttribute("user")
    public User getUser(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getUserPrincipal().getName());

        return user;
    }

    @GetMapping("list")
    public ModelAndView listContracts(@PageableDefault(value = 5) Pageable pageable) {
        Page<Contract> contracts = contractService.findAll(pageable);
        ContractDetail contractDetail = new ContractDetail();
        ModelAndView modelAndView = new ModelAndView("views/contract/list");
        modelAndView.addObject("contracts",contracts);
        modelAndView.addObject("contractDetail",contractDetail);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createForm() {
        Contract contract = new Contract();
        return new ModelAndView("views/contract/createForm","contract",contract);
    }
    @PostMapping("create")
    public String createContract(@Valid @ModelAttribute Contract contract, BindingResult bindingResult) {
        new Contract().validate(contract,bindingResult);
        if(bindingResult.hasErrors()) {
            return "views/contract/createForm";
        }else {
            contractService.save(contract);
            return "redirect:/contracts/list";
        }
    }
}
