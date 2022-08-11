package blog_app.controller.restController;

import blog_app.entity.Blog;
import blog_app.entity.Category;
import blog_app.service.BlogService;
import blog_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/categories")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BlogService blogService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAllCategories(@PageableDefault(value = 5) Pageable pageable) {
        Page<Category> categoryList = categoryService.findAll(pageable);
        if(categoryList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(categoryList,HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Page<Blog>> viewCategory(@PathVariable("id") int id, Pageable pageable) {
        Category category = categoryService.findById(id);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Page<Blog> blogList = blogService.findAllByCategory(id,pageable);
            return new ResponseEntity<>(blogList,HttpStatus.OK);
//            return new ResponseEntity<>(category.getBlogs(),HttpStatus.OK);
        }
    }

}
