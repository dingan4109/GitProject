import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  interceptorToken: string;

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

  setInterceptorToken(token: string) {
    this.interceptorToken = token;
  }

  saveUsernameLocal(username) {
    window.localStorage.removeItem("username");
    window.localStorage.setItem("username", username);
  }

  saveUsernameSession(username) {
    window.sessionStorage.removeItem("username");
    window.sessionStorage.setItem("username",username);
  }

  getUsername() {
    let username = localStorage.getItem("username");
    if(username !== null) {
      return username;
    }else {
      return sessionStorage.getItem("username");
    }
  }
}
