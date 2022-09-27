import { Component, OnInit } from '@angular/core';
import {Blog} from "../model/blog";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-create-blog',
  templateUrl: './create-blog.component.html',
  styleUrls: ['./create-blog.component.css']
})
export class CreateBlogComponent implements OnInit {
  blog: Blog;
blogForm: FormGroup;
  constructor() { }

  ngOnInit(): void {
    this.blogForm = new FormGroup({
      title: new FormControl(),
      image: new FormControl(),
      description: new FormControl(),
      content: new FormControl()
    })
  }

  createBlog() {
    this.blog = this.blogForm.value;
    this.blogForm.reset();
    console.log(this.blog);
  }
}
