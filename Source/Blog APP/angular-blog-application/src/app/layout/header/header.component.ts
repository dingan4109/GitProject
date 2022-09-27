import { Component, OnInit } from '@angular/core';
import {BlogService} from "../../service/blog.service";
import {CategoryService} from "../../service/category.service";
import {Category} from "../../model/category";
import {BlogListComponent} from "../../blog/blog-list/blog-list.component";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  categories: Category[] = [];
  currentPage: 0;
  itemsPerPage: 5;

  constructor(private blogService: BlogService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.getAllCategory();
  }

  getAllCategory() {
    this.categoryService.getAllCategories(0, 5).subscribe(categories => this.categories = categories.content);
  }

  categoryId(categoryId: number) {
    this.blogService.categoryId = categoryId;
  }
}
