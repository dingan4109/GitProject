import { Component, OnInit } from '@angular/core';
import {Blog} from "../../model/blog";
import {BlogService} from "../../service/blog.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NotifierService} from "angular-notifier";
import {AngularFireStorage} from "@angular/fire/storage";

@Component({
  selector: 'app-blog-view',
  templateUrl: './blog-view.component.html',
  styleUrls: ['./blog-view.component.css']
})
export class BlogViewComponent implements OnInit {
  blog: Blog = {};
  id: number;
  notifier: NotifierService;

  constructor(private blogService: BlogService, private activatedRoute: ActivatedRoute, private router: Router, private notifierService: NotifierService, private storage: AngularFireStorage) {
    this.notifier = notifierService;
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
    this.storage.storage.refFromURL(this.blog.image).delete();
    this.blogService.deleteBlog(this.id).subscribe(
      () => {},
      () => {},
      () => {
        this.router.navigateByUrl('/blog-list');
        this.notifier.notify('default','Blog is successfully deleted!')
      }
      )
  }

  like() {
    this.blog.likeNumber++;
    this.blogService.increaseLike(this.id, this.blog).subscribe(
      () => {},
      () => {},
      () => {},
    )
  }
}
