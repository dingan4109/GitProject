package case_study_2.controller;

import case_study_2.entity.Customer;
import case_study_2.entity.CustomerType;
import case_study_2.service.customer.CustomerService;
import case_study_2.service.customer.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerTypeService customerTypeService;

    @ModelAttribute("customerTypes")
    public Page<CustomerType> getCustomerTypes(Pageable pageable) {
        Page<CustomerType> customerTypes = customerTypeService.findAll(pageable);
        return customerTypes;
    }

    @GetMapping("list")
    public ModelAndView listCustomers(@PageableDefault(value = 5) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("views/customer/list");
        modelAndView.addObject("customers",customers);

        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createForm(){
        Customer customer = new Customer();
        return new ModelAndView("views/customer/createForm","customer",customer);
    }

    @PostMapping("create")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if(!customer.isPresent()) {
            return new ModelAndView("views/error-404");
        }else {
            return new ModelAndView("views/customer/editForm","customer",customer.get());
        }
    }

    @PostMapping("edit")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers/list";
    }

    @PostMapping("delete")
    public String deleteCustomer(@RequestParam("id") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if(!customer.isPresent()) {
            return "views/error-404";
        }else {
            customerService.deleteById(id);
            return "redirect:/customers/list";
        }
    }

}
