import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private authService: AuthService) { }

  signOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
    this.authService.isLoggedIn = false;
    this.authService.roles = null;
    this.authService.username.next(null);
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
    window.localStorage.setItem("account", account);
  }

  saveAccountSession(account) {
    window.sessionStorage.removeItem("account");
    window.sessionStorage.setItem("account",account);
  }

  getAccount() {
    let account = localStorage.getItem("account");
    if(account !== null) {
      return account;
    }else {
      return sessionStorage.getItem("account");
    }
  }

}
