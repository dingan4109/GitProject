import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {Injectable} from "@angular/core";
import {TokenService} from "../service/token.service";

@Injectable()
export class LoginCheck implements CanActivate{

  constructor(private tokenService: TokenService ) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree {
    if(this.tokenService.getLoginStatus() == "true" && this.tokenService.getAccount().roles.includes("ROLE_USER")) {
      return true;
    }else {
      window.alert("You don't have permission to view this page");
      return false;
    }
  }
}
