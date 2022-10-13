import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BlogModule} from "./blog/blog.module";
import {CategoryModule} from "./category/category.module";
import {LayoutModule} from "./layout/layout.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NotifierModule} from "angular-notifier";
import {AngularFireModule} from "@angular/fire";
import {environment} from "../environments/environment";
import {CommentModule} from "./comment/comment.module";
import {SecurityModule} from "./security/security.module";
import {AuthInterceptor} from "./security/interceptors/auth-interceptor";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BlogModule,
    CategoryModule,
    LayoutModule,
    HttpClientModule,
    NotifierModule.withConfig({
      theme: 'material'
    }),
    AngularFireModule.initializeApp(environment.firebaseConfig),
    CommentModule,
    SecurityModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
