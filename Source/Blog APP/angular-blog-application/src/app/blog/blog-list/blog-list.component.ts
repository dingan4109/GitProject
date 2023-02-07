import {Component, OnInit} from '@angular/core';
import {Blog} from "../../model/blog";
import {BlogService} from "../../service/blog.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenService} from "../../service/token.service";

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
  categoryId: number;

  constructor(private blogService: BlogService, private router: Router, private tokenService: TokenService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      this.categoryId = Number(paramMap.get('id'));
      if (this.categoryId) {
        console.log("category");
        this.getBlogsByCategory()
      } else {
        this.getAllBlogs();
      }
      if (this.tokenService.getAccount()) {
        this.roles = this.tokenService.getAccount().roles;
      } else {
        this.roles = null;
      }
    })
  }

  private getAllBlogs() {
    this.blogService.getAllBlogs(this.config.currentPage - 1, this.config.itemsPerPage).subscribe(blogs => {
      this.blogs = blogs.content;
      this.config.totalItems = blogs.totalElements;
    })
  }

  pageChanged(event: number) {
    this.config.currentPage = event;
    this.ngOnInit();
  }

  getBlogsByCategory() {
    this.blogService.findBlogsByCategory(this.categoryId, this.config.currentPage - 1, this.config.itemsPerPage).subscribe((blogs) => {
        this.blogs = blogs.content;
        this.config.totalItems = blogs.totalElements;
      },
      (error) => {
        if (error.status === 404) {
          this.router.navigateByUrl('error');
        }
      },
      () => {}
    );
  }

}
