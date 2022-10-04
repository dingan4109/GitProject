import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecurityRoutingModule } from './security-routing.module';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import {LayoutModule} from "../layout/layout.module";


@NgModule({
  declarations: [LoginComponent],
    imports: [
        CommonModule,
        SecurityRoutingModule,
        ReactiveFormsModule,
        LayoutModule
    ]
})
export class SecurityModule { }
