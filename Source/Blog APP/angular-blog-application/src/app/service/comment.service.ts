import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../model/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  API_COMMENT = 'http://localhost:8080/comments';

  constructor(private http: HttpClient) {
  }

  getAllComments(currentPage, itemsPerPage): Observable<Response> {
    return this.http.get<Response>(`${this.API_COMMENT}?page=${currentPage}&size=${itemsPerPage}`);
  }
  findCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.API_COMMENT}/${id}`);
  }
  createComment(comment: Comment): Observable<void>{
    return this.http.post<void>(`${this.API_COMMENT}/create`, comment);
  }

  updateComment(id: number, comment: Comment): Observable<void>{
    return this.http.patch<void>(`${this.API_COMMENT}/update/${id}`, comment);
  }

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_COMMENT}/delete/${id}`)
  }

  findAllCommentByBlog(blogId, currentPage, itemsPerPage): Observable<Response> {
    return this.http.get<Response>(`${this.API_COMMENT}/search?blogId=${blogId}&page=${currentPage}&size=${itemsPerPage}`)
  }
}
interface Response {
  content: Comment[];
  totalElements: number;
}

