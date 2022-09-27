import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BlogServiceService} from "../service/blog-service.service";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-view-blog',
  templateUrl: './view-blog.component.html',
  styleUrls: ['./view-blog.component.css']
})
export class ViewBlogComponent implements OnInit {
  blog: any;

  constructor(private blogService: BlogServiceService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      const id = paramMap.get('id');
      this.blog = this.blogService.selectBlogById(parseInt(id));
    });
  }

}
