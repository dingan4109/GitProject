import {AuthService} from "../service/auth.service";
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable()
export class AdminCheck implements CanActivate{

  constructor(private authService: AuthService ) {
}
canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
  if(this.authService.isLoggedIn && this.authService.roles.includes("ROLE_ADMIN")) {
    return true;
  }else {
    window.alert("You don't have permission to view this page");
    return false;
  }
}
}
