package blog_app.controller;


import blog_app.entity.Category;
import blog_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView categoryList(@PageableDefault(value = 5) Pageable pageable) {
        Page<Category> categoryList = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("categories/categoryList");
        modelAndView.addObject("categoryList",categoryList);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView write() {
        Category category = new Category();
        ModelAndView modelAndView = new ModelAndView("categories/create");
        modelAndView.addObject("category",category);

        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories/list";
    }


    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        Category category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("categories/edit");
        modelAndView.addObject("category",category);

        return modelAndView;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/categories/list";
    }
}
