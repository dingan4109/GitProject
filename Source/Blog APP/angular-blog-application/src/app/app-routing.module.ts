import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BlogRoutingModule} from "./blog/blog-routing.module";


const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes),BlogRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
