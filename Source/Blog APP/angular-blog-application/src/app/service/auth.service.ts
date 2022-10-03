import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Blog} from "../model/blog";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  AUTH_API = 'http://localhost:8080/login';
  httpOptions: any;
  isLoggedIn: boolean;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };

  }


  login(obj): Observable<any> {
    console.log("login")
    let loginObject = new URLSearchParams();
    loginObject.set("username", obj.username);
    loginObject.set("password", obj.password);
    return this.http.post(`${this.AUTH_API}`,loginObject.toString(),this.httpOptions)
  }
}
