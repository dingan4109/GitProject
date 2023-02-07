import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  signOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
  }

  saveTokenLocal(token: string) {
    console.log(123);
    window.localStorage.removeItem("token");
    window.localStorage.setItem("token",token);
  }

  saveTokenSession(token: string) {
    window.sessionStorage.removeItem("token");
    window.sessionStorage.setItem("token", token);
  }

  getToken(): string {
    let token = localStorage.getItem("token");
    if(token !== null) {
      return token;
    }else {
      return sessionStorage.getItem("token");
    }
  }

  saveAccountLocal(account) {
    window.localStorage.removeItem("account");
    window.localStorage.setItem("account", JSON.stringify(account));
  }

  loginStatusLocal(status: string) {
    window.localStorage.removeItem("isLoggedIn");
    window.localStorage.setItem("isLoggedIn", status);
  }

  saveAccountSession(account) {
    window.sessionStorage.removeItem("account");
    window.sessionStorage.setItem("account",JSON.stringify(account));
  }

  loginStatusSession(status: string) {
    window.sessionStorage.removeItem("isLoggedIn");
    window.sessionStorage.setItem("isLoggedIn", status);
  }

  getAccount() {
    let account = localStorage.getItem("account");
    if(account !== null) {
      return JSON.parse(account);
    }else {
      return JSON.parse(sessionStorage.getItem("account"));
    }
  }

  getLoginStatus(): string {
    let status = localStorage.getItem("isLoggedIn");
    if(status !== null) {
      return status;
    }else {
      return sessionStorage.getItem("isLoggedIn");
    }
  }

}
