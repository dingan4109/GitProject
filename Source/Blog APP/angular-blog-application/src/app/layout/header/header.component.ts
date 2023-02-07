import {Component, OnInit} from '@angular/core';
import {BlogService} from "../../service/blog.service";
import {CategoryService} from "../../service/category.service";
import {Category} from "../../model/category";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  categories: Category[] = [];
  username = null;

  constructor(private blogService: BlogService, private categoryService: CategoryService, private tokenService : TokenService, private router: Router) {

  }

  ngOnInit(): void {
    this.getAllCategory();
    if(this.tokenService.getAccount()) {
      this.username = this.tokenService.getAccount().username;
    }else{
      this.username = null;
    }
  }

  getAllCategory() {
    this.categoryService.getAllCategories(0, 5).subscribe(categories => this.categories = categories.content);
  }

  logout() {
    if(window.confirm("Confirm logout?")) {
      this.tokenService.signOut();
      this.ngOnInit();
      this.router.navigateByUrl('');
    }
  }
}
