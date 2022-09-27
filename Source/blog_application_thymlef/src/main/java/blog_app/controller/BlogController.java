package blog_app.controller;

import blog_app.entity.Blog;
import blog_app.entity.Category;
import blog_app.service.BlogService;
import blog_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Page<Category> categories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/blogs")
    public ModelAndView blogList(@PageableDefault(value = 4) Pageable pageable) {
        Page<Blog> blogList;
//        if(!searchByTitle.isPresent()) {
//            blogList = blogService.findAll(pageable);
//        }else {
//            blogList = blogService.search(searchByTitle.get(),pageable);
//        }
        blogList = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("page","list");
        modelAndView.addObject("blogList",blogList);

        return modelAndView;
    }

    @GetMapping("/write")
    public ModelAndView write() {
        Blog blog = new Blog();
        ModelAndView modelAndView = new ModelAndView("write");
        modelAndView.addObject("page","write");
        modelAndView.addObject("blog",blog);

        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") int id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("page","view");
        modelAndView.addObject("blog",blog);

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("page","edit");
        modelAndView.addObject("blog",blog);

        return modelAndView;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }

    @GetMapping("/category/{id}")
    public ModelAndView listByCategory(@PathVariable("id") int id,@PageableDefault(value = 4) Pageable pageable) {
       Page<Blog> blogList = blogService.findAllByCategory(id,pageable);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("page","list");
        modelAndView.addObject("blogList",blogList);

        return modelAndView;
    }
}
