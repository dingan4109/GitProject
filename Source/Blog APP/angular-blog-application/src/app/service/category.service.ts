import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Category} from "../model/category";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  API_CATEGORY = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) {
  }

  getAllCategories(currentPage, itemsPerPage): Observable<Response> {
    return this.http.get<Response>(`${this.API_CATEGORY}?page=${currentPage}&size=${itemsPerPage}`);
  }
  findCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.API_CATEGORY}/view/${id}`);
  }
  createCategory(category: Category): Observable<void>{
    return this.http.post<void>(`${this.API_CATEGORY}`, category);
  }

  updateCategory(id: number, category: Category): Observable<void>{
    return this.http.patch<void>(`${this.API_CATEGORY}/${id}`, category);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_CATEGORY}/${id}`)
  }
}

interface Response {
  content: Category[];
  totalElements: number;
}
