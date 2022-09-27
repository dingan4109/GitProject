import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {filter, map} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'angular-blog-application';

  // ngOnInit() {
  //   this.router.events
  //     .pipe(
  //       filter((event) => event instanceof NavigationEnd),
  //       map(() => {
  //         let route: ActivatedRoute = this.router.routerState.root;
  //         let routeTitle = '';
  //         while(route!.firstChild) {
  //           routeTitle = route!.snapshot.data['title'];
  //         }
  //         return routeTitle;
  //       })
  //     )
  //     .subscribe((title: string) => {
  //       if(title) {
  //         this.titleService.setTitle(`My App - ${title}`);
  //       }
  //     });
  // }
}
