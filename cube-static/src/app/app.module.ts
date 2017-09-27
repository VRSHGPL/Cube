import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';

import { routes } from './app.routes';
import { LoginModule } from './login/login.module';
import { DashboardModule } from './dashboard/dashboard.module';


@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot(routes),
        LoginModule,
        DashboardModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
    
export class AppModule { }
