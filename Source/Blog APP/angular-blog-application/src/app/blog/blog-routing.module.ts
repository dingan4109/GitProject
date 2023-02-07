import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BlogListComponent} from "./blog-list/blog-list.component";
import {BlogCreateComponent} from "./blog-create/blog-create.component";
import {BlogViewComponent} from "./blog-view/blog-view.component";
import {BlogEditComponent} from "./blog-edit/blog-edit.component";
import {AdminCheck} from "../security/admin-check";


const routes: Routes = [
  {path: 'blog-list', component: BlogListComponent, data: {title: 'List'}},
  {path: 'blog-list/:id', component: BlogListComponent},
  {path: 'blog-create', component: BlogCreateComponent, canActivate: [AdminCheck]},
  {path: 'blog-view/:id', component: BlogViewComponent},
  {path: 'blog-edit/:id', component: BlogEditComponent, canActivate: [AdminCheck]},
  {path: '', component: BlogListComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlogRoutingModule { }

