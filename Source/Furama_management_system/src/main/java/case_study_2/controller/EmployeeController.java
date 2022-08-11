package case_study_2.controller;

import case_study_2.entity.*;
import case_study_2.service.employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;
    @Autowired
    EducationDegreeService educationDegreeService;
    @Autowired
    DivisionService divisionService;
    @Autowired
    UserService userService;

    @ModelAttribute("positions")
    public Page<Position> getPositions(Pageable pageable) {
        Page<Position> positions = positionService.findAll(pageable);
        return positions;
    }
    @ModelAttribute("educationDegrees")
    public Page<EducationDegree> getEducationDegree(Pageable pageable) {
        Page<EducationDegree> educationDegrees = educationDegreeService.findAll(pageable);
        return educationDegrees;
    }
    @ModelAttribute("divisions")
    public Page<Division> getDivision(Pageable pageable) {
        Page<Division> divisions = divisionService.findAll(pageable);
        return divisions;
    }
    @ModelAttribute("users")
    public Page<User> getUser(Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        return users;
    }

    @GetMapping("list")
    public ModelAndView listEmployees(@PageableDefault(value = 5) Pageable pageable) {
        Page<Employee> employees = employeeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("views/employee/list");
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createForm() {
        Employee employee = new Employee();
        return new ModelAndView("views/employee/createForm","employee",employee);
    }
    @PostMapping("create")
    public String createEmployee(@ModelAttribute Employee employee) {
        User user = new User();
        user.setUsername(employee.getEmployeeEmail());
        user.setPassword("$2a$10$e7CY.RspuVZXLbXRPKS7PeyFO36xs3/fwD2DsLnkrO6YedtMB1iVi");
        userService.save(user);

        employee.setUser(user);
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("edit/{id}")
    public ModelAndView editEmployee(@PathVariable("id") Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        ModelAndView modelAndView;
        if(!employee.isPresent()) {
            modelAndView = new ModelAndView("views/error-404");
        }else {
            Integer userId = employee.get().getUser().getUserId();
            modelAndView = new ModelAndView("views/employee/editForm");
            modelAndView.addObject("employee",employee.get());
            modelAndView.addObject("userId",userId);
        }
        return modelAndView;
    }
    @PostMapping("edit")
    public String updateEmployee(@ModelAttribute Employee employee, @RequestParam("userId") Integer userId) {
       User user = userService.findById(userId).get();
        if(!user.getUsername().equals(employee.getEmployeeEmail())) {
            user.setUsername(employee.getEmployeeEmail());
        }
        employee.setUser(user);
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @PostMapping("delete")
    public String deleteEmployee(@RequestParam("id") Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        if(!employee.isPresent()) {
            return "views/error-404";
        }else {
            userService.deleteById(employee.get().getUser().getUserId());
            employeeService.deleteById(id);
            return "redirect:/employees/list";
        }
    }
}
