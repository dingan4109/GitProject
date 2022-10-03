package angular_blog_application_BE.restController;

import angular_blog_application_BE.entity.Category;
import angular_blog_application_BE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAllCategories(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        if(categories.hasContent()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("view/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
