import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from "../../service/comment.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlogService} from "../../service/blog.service";
import {Router} from "@angular/router";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-comment-create',
  templateUrl: './comment-create.component.html',
  styleUrls: ['./comment-create.component.css']
})
export class CommentCreateComponent implements OnInit {
  commentForm: FormGroup;
  @Input()
  blogId: number;
  reload = 0;

  constructor(private commentService: CommentService, private blogService: BlogService, private fb: FormBuilder, private router: Router, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.commentForm = this.fb.group({
      author: ['',[Validators.required]],
      content: ['',[Validators.required]],
      blog: ''
    })
  }

  submit() {
    if(this.tokenService.getAccount()) {
      this.blogService.findBlogById(this.blogId).subscribe(blog => {
        this.commentForm.patchValue({blog: blog})
        const comment = this.commentForm.value;
        this.commentService.createComment(comment).subscribe(
          () => {},
          () => {},
          () => {
            this.commentForm.reset();
            this.reload++;
          }
        )
      })
    }else {
      if(window.confirm("Please log in to comment the post!")) {
        this.router.navigateByUrl("/login");
      }else {
        this.router.navigateByUrl("/blog-view/" + this.blogId);
      }
    }

  }
}
