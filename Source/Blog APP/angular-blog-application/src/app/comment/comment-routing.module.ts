import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommentListComponent} from "./comment-list/comment-list.component";
import {CommentCreateComponent} from "./comment-create/comment-create.component";
import {LoginCheck} from "../security/login-check";


const routes: Routes = [
  {path: "comment-list", component: CommentListComponent},
  {path: "comment-create", component: CommentCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CommentRoutingModule { }
