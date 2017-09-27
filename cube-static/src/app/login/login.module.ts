import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';

@NgModule({
    imports: [RouterModule],
    declarations: [
        LoginComponent
    ],
    exports: [LoginComponent]

})
export class LoginModule { }
