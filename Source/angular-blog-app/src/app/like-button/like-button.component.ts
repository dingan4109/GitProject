import {Component, Input, OnInit} from '@angular/core';
import {LikeButtonService} from "../service/like-button.service";
import {BlogServiceService} from "../service/blog-service.service";
import {Blog} from "../model/blog";

@Component({
  selector: 'app-like-button',
  templateUrl: './like-button.component.html',
  styleUrls: ['./like-button.component.css']
})
export class LikeButtonComponent implements OnInit {
  isLiked = false;
  likeCount:number;
  @Input() blogId: number;
  blog:Blog;

  constructor(private blogService: BlogServiceService) {
  }

  ngOnInit(): void {
    this.blog = this.blogService.selectBlogById(this.blogId);
    this.likeCount = this.blog.like;
  }

  like() {
    if(!this.isLiked) {
      this.blogService.increaseLike(this.blogId);
      this.likeCount = this.blog.like;
    }else {
      this.blogService.decreaseLike(this.blogId);
      this.likeCount = this.blog.like;
    }

    this.isLiked = !this.isLiked;
  }
}
