import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlogRoutingModule } from './blog-routing.module';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogCreateComponent } from './blog-create/blog-create.component';
import { BlogEditComponent } from './blog-edit/blog-edit.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import { BlogViewComponent } from './blog-view/blog-view.component';
import {CommentModule} from "../comment/comment.module";
import {ErrorModule} from "../error/error.module";
import {AdminCheck} from "../security/admin-check";
import {LoginCheck} from "../security/login-check";
import {AuthInterceptor} from "../security/interceptors/auth-interceptor";


@NgModule({
  declarations: [BlogListComponent, BlogCreateComponent, BlogEditComponent, BlogViewComponent],
  imports: [
    CommonModule,
    BlogRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    CommentModule,
    ErrorModule
  ],
  providers: [AdminCheck, LoginCheck, AuthInterceptor]
})
export class BlogModule { }
