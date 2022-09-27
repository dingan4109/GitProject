package angular_blog_application_BE.restController;

import angular_blog_application_BE.entity.Comment;
import angular_blog_application_BE.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Comment>> findAllComment(@PageableDefault(value = 5, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<Comment> comments = commentService.findAll(pageable);
        if(comments.hasContent()) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable("id") Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if(comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createComment(@Valid @RequestBody Comment comment, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }else {
            commentService.save(comment);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping("search")
    public ResponseEntity<Page<Comment>> findAllCommentByBlog(@RequestParam("blogId") Long blogId, @PageableDefault(value = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Comment> comments = commentService.findAllByBlog(blogId, pageable);
        if(comments.hasContent()) {
            return new ResponseEntity<>(comments,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
