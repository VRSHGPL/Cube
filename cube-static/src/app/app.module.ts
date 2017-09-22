import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
//import { LoginModule } from './login/login.module';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
    
    {
        path: '', component: LoginComponent
     },
    {
        path: 'dashBoard', component: DashboardComponent
     },
    {
        path: '**',  redirectTo: ''
     }
    
 ];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        DashboardComponent
  ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot(routes),
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }