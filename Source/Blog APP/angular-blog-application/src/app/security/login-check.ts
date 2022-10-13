import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {Injectable} from "@angular/core";

@Injectable()
export class LoginCheck implements CanActivate{

  constructor(private authService: AuthService ) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    if(this.authService.isLoggedIn && this.authService.roles.includes("ROLE_USER")) {
      return true;
    }else {
      window.alert("You don't have permission to view this page");
      return false;
    }
  }
}
