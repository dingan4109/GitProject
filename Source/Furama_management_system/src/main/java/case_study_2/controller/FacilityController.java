package case_study_2.controller;

import case_study_2.entity.Facility;
import case_study_2.entity.FacilityType;
import case_study_2.entity.RentType;
import case_study_2.service.facility.FacilityService;
import case_study_2.service.facility.FacilityTypeService;
import case_study_2.service.facility.RentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String saveFacility(@ModelAttribute Facility facility) {
        facilityService.save(facility);
        return "redirect:/facilities/list";
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
    public String updateFacility(@ModelAttribute Facility facility) {
        facilityService.save(facility);
        return "redirect:/facilities/list";
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
