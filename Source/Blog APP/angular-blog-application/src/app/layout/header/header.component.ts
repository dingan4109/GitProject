import {Component, OnInit} from '@angular/core';
import {BlogService} from "../../service/blog.service";
import {CategoryService} from "../../service/category.service";
import {Category} from "../../model/category";
import {AuthService} from "../../service/auth.service";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  categories: Category[] = [];
  username = null;

  constructor(private blogService: BlogService, private categoryService: CategoryService, private authService: AuthService, private tokenService : TokenService) {
    this.authService.username.subscribe(name => this.username = name)
  }

  ngOnInit(): void {
    this.getAllCategory();
  }

  getAllCategory() {
    this.categoryService.getAllCategories(0, 5).subscribe(categories => this.categories = categories.content);
  }

  categoryId(categoryId: number) {
    this.blogService.categoryId = categoryId;
  }

  logout() {
    this.tokenService.signOut();
  }
}
