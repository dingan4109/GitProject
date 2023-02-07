import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenService} from "../../service/token.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  interceptorToken: string;

  constructor(private tokenService: TokenService) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   this.interceptorToken = this.tokenService.getToken();
    console.log("CHECK TOKEN" + this.interceptorToken);
    if(!this.interceptorToken) {
      return next.handle(req);
    }
    const req1 = req.clone({
      setHeaders: {
        'Authorization': `Bearer ${this.interceptorToken}`,
      },
      // withCredentials: true
    });
    return next.handle(req1);
  }
}
