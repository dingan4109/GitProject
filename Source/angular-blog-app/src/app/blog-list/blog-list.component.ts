import {Component, OnInit} from '@angular/core';
import {Blog} from "../model/blog";
import {BlogServiceService} from "../service/blog-service.service";

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {
blogs: Blog[];

  constructor(private blogService: BlogServiceService) {
  }

  ngOnInit(): void {
    this.blogs = this.blogService.blogs;
  }

}
