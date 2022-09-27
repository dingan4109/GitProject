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


@NgModule({
  declarations: [BlogListComponent, BlogCreateComponent, BlogEditComponent, BlogViewComponent],
  imports: [
    CommonModule,
    BlogRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    CommentModule
  ]
})
export class BlogModule { }
