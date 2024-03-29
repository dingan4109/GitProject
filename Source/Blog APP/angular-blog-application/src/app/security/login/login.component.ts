import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {TokenService} from "../../service/token.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private tokenService: TokenService, private router: Router) {
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: '',
      password: '',
      rememberMe: '',
    });
  }

  submitLogin() {
    if (this.tokenService.getAccount()!== null) {
      this.logout();
    }
    this.authService.login(this.loginForm.value).subscribe(data => {
        if (this.loginForm.value.rememberMe) {
          this.tokenService.saveTokenLocal(data.access_token);
          this.tokenService.saveAccountLocal(data);
          this.tokenService.loginStatusLocal("true");
        } else {
          this.tokenService.saveTokenSession(data.access_token);
          this.tokenService.saveAccountSession(data);
          this.tokenService.loginStatusSession("true");
        }
        window.location.assign('');
      },
      () => {
      },
      () => {
        this.router.navigateByUrl("");
      },
    )
  }

  logout() {
    this.tokenService.signOut();
  }
}
