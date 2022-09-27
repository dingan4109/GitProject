import {Component, OnInit} from '@angular/core';
import {Blog} from "../../model/blog";
import {BlogService} from "../../service/blog.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {
  blogs: Blog[] = [];
  config = {
    itemsPerPage: 8,
    currentPage: 1,
    totalItems: 0
  }

  constructor(private blogService: BlogService, private router: Router) { }

  ngOnInit(): void {
    this.getAllBlogs();
 }

  private getAllBlogs() {
    this.blogService.getAllBlogs(this.config.currentPage-1, this.config.itemsPerPage).subscribe(blogs => {
      this.blogs = blogs.content;
      this.config.totalItems = blogs.totalElements;
    })
  }

  pageChanged(event: number) {
    this.config.currentPage = event;
    this.ngOnInit();
  }

   getBlogsByCategory() {
    this.blogService.getBlogsByCategory().subscribe(blogs => this.blogs = blogs);
  }
}
