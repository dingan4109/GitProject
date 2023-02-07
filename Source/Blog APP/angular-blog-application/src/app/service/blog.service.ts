import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Blog} from "../model/blog";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BlogService {
  API_BLOG = 'http://localhost:8080/blogs';

  constructor(private http: HttpClient) {
  }

  getAllBlogs(currentPage, itemsPerPage): Observable<Response> {
    return this.http.get<Response>(`${this.API_BLOG}?page=${currentPage}&size=${itemsPerPage}`);
  }
  findBlogById(id: number): Observable<Blog> {
    return this.http.get<Blog>(`${this.API_BLOG}/view/${id}`);
  }
  createBlog(blog: Blog): Observable<any>{
    return this.http.post<any>(`${this.API_BLOG}/create`, blog);
  }

  updateBlog(id: number, blog: Blog): Observable<void>{
    return this.http.patch<void>(`${this.API_BLOG}/update/${id}`, blog);
  }

  deleteBlog(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_BLOG}/delete/${id}`)
  }
  findBlogsByCategory(categoryId: number, currentPage, itemsPerPage): Observable<Response> {
    return this.http.get<Response>(`${this.API_BLOG}/findBlogByCategory/${categoryId}?page=${currentPage}&size=${itemsPerPage}`)
  }
  increaseLike(id, blog): Observable<void> {
    return this.http.patch<void>(`${this.API_BLOG}/like/${id}`,blog);
  }
}
interface Response {
  content: Blog[];
  totalElements: number;
}
