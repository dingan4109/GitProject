import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {CommentService} from "../../service/comment.service";
import {Comment} from "../../model/comment";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit, OnChanges {
  comments: Comment[] = [];
  config = {
    itemsPerPage: 5,
    currentPage: 1,
    totalItems: 0
  }
  @Input()
  reload = 0;
  @Input()
  blogId: number;

  constructor(private commentService: CommentService) { }

  ngOnInit(): void {
    this.findAllCommentByBlog();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.ngOnInit();
  }


  pageChanged(event: number) {
    this.config.currentPage = event;
    this.ngOnInit();
  }


  private findAllCommentByBlog() {
    this.commentService.findAllCommentByBlog(this.blogId,this.config.currentPage -1, this.config.itemsPerPage).subscribe(comments => {
      this.comments = comments.content;
      this.config.totalItems = comments.totalElements;
    })
  }
}
