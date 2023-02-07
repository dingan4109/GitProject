package angular_blog_application_BE.restController;

import angular_blog_application_BE.entity.Blog;
import angular_blog_application_BE.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("blogs")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping
    public ResponseEntity<Page<Blog>> findAllBlog(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        if (blogs.hasContent()) {
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("view/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable("id") Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            return new ResponseEntity<>(blog.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("findBlogByCategory/{categoryId}")
    public ResponseEntity<Page<Blog>> findBlogByCategory(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable("categoryId") Long categoryId) {
        System.out.println("check");
        Page<Blog> blogs = this.blogService.findBlogByCategory(categoryId, pageable);
        if(blogs.hasContent()) {
            System.out.println(blogs);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createBlog(@Valid @RequestBody Blog blog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            blogService.save(blog);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable("id") Long id, @Valid @RequestBody Blog updateBlog, BindingResult bindingResult) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                blogService.save(updateBlog);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            blogService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("like/{id}")
    public ResponseEntity<?> increaseLike(@PathVariable("id") Long id, @RequestBody Blog blog) {
        Optional<Blog> foundBlog = blogService.findById(id);
        if (foundBlog.isPresent()) {
            blogService.increaseLike(id, blog.getLikeNumber());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
