import { Component, OnInit } from '@angular/core';
import {Blog} from "../../model/blog";
import {BlogService} from "../../service/blog.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NotifierService} from "angular-notifier";
import {AngularFireStorage} from "@angular/fire/storage";
import {AuthService} from "../../service/auth.service";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-blog-view',
  templateUrl: './blog-view.component.html',
  styleUrls: ['./blog-view.component.css']
})
export class BlogViewComponent implements OnInit {
  blog: Blog = {};
  id: number;
  notifier: NotifierService;
  roles: string[];

  constructor(private blogService: BlogService, private activatedRoute: ActivatedRoute, private router: Router, private notifierService: NotifierService, private storage: AngularFireStorage, private authService: AuthService, private tokenService: TokenService) {
    this.notifier = notifierService;
    if(this.tokenService.getAccount()) {
      this.roles = this.tokenService.getAccount().roles;
    }else {
      this.roles = null;
    }
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      this.id = Number(paramMap.get('id'));
      this.blogService.findBlogById(this.id).subscribe(blog => {
        this.blog = blog;
      })
    })
  }

  delBlog() {
    if(this.blog.image) {
      this.storage.storage.refFromURL(this.blog.image).delete();
    }
    this.blogService.deleteBlog(this.id).subscribe(
      () => {},
      () => {},
      () => {
        this.router.navigateByUrl('/blog-list');
        this.notifier.notify('default','Blog is successfully deleted!');
      }
      )
  }

  like() {
    if(this.roles !== null) {
      this.blog.likeNumber++;
      this.blogService.increaseLike(this.id, this.blog).subscribe(
        () => {},
        () => {},
        () => {},
      )
    }else {
      if(window.confirm("Please log in to like the post!")) {
        this.router.navigateByUrl("/login");
      }else {
        this.router.navigateByUrl("/blog-view/" + this.id);
      }
    }

  }
}
