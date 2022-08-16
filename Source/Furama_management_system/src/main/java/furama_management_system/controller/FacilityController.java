package furama_management_system.controller;

import furama_management_system.entity.Facility;
import furama_management_system.entity.FacilityType;
import furama_management_system.entity.RentType;
import furama_management_system.entity.User;
import furama_management_system.service.facility.FacilityService;
import furama_management_system.service.facility.FacilityTypeService;
import furama_management_system.service.facility.RentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("facilities")
public class FacilityController {
    @Autowired
    FacilityService facilityService;
    @Autowired
    FacilityTypeService facilityTypeService;
    @Autowired
    RentTypeService rentTypeService;
    @ModelAttribute("facilityTypes")
    public Page<FacilityType> getFacilityTypes(Pageable pageable) {
        Page<FacilityType> facilityTypes = facilityTypeService.findAll(pageable);
        return facilityTypes;
    }
    @ModelAttribute("rentTypes")
    public Page<RentType> getRentTypes(Pageable pageable) {
        Page<RentType> rentTypes = rentTypeService.findAll(pageable);
        return rentTypes;
    }

    @ModelAttribute("user")
    public User getUser(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getUserPrincipal().getName());

        return user;
    }

    @GetMapping("list")
    public ModelAndView listFacilities(@PageableDefault(value = 5) Pageable pageable) {
        Page<Facility> facilities = facilityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("views/facility/list");
        modelAndView.addObject("facilities",facilities);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createFacilityForm() {
        Facility facility = new Facility();
        return new ModelAndView("views/facility/createForm","facility",facility);
    }

    @PostMapping("create")
    public String saveFacility(@Valid @ModelAttribute Facility facility, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "views/facility/createForm";
        }else {
            facilityService.save(facility);
            return "redirect:/facilities/list";
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Integer id){
        Optional<Facility> facility = facilityService.findById(id);
        if(!facility.isPresent()) {
            return new ModelAndView("views/errors-404");
        }else{
            return new ModelAndView("views/facility/editForm","facility",facility.get());
        }
    }
    @PostMapping("edit")
    public String updateFacility(@Valid @ModelAttribute Facility facility, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "views/facility/editForm";
        }else {
            facilityService.save(facility);
            return "redirect:/facilities/list";
        }
    }

    @PostMapping("delete")
    public String deleteFacility(@RequestParam("id") Integer id) {
        Optional<Facility> facility = facilityService.findById(id);
        if(!facility.isPresent()) {
            return "views/errors-404";
        }else{
            facilityService.deleteById(id);
            return "redirect:/facilities/list";
        }
    }


}
