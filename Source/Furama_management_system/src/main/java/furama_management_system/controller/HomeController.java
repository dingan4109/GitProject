package furama_management_system.controller;

import furama_management_system.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/customers/home";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("views/login");
    }
}
