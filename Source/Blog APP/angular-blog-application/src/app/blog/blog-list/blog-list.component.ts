import {Component, OnInit} from '@angular/core';
import {Blog} from "../../model/blog";
import {BlogService} from "../../service/blog.service";
import {Router} from "@angular/router";
import {TokenService} from "../../service/token.service";
import {AuthService} from "../../service/auth.service";

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
  roles: string[];

  constructor(private blogService: BlogService, private router: Router, private tokenService: TokenService, private authService: AuthService) { }

  ngOnInit(): void {
    this.getAllBlogs();
    this.roles = this.authService.roles;
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

  out() {
    this.tokenService.signOut();

  }
}
