import {AuthService} from "../service/auth.service";
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Injectable} from "@angular/core";
import {TokenService} from "../service/token.service";

@Injectable()
export class AdminCheck implements CanActivate{

  constructor(private tokenService: TokenService ) {
}
canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
  if(this.tokenService.getLoginStatus() == "true" && this.tokenService.getAccount().roles.includes("ROLE_ADMIN")) {
    return true;
  }else {
    window.alert("You don't have permission to view this page");
    return false;
  }
}
}
