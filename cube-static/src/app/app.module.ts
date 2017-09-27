import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
//import { LoginModule } from './login/login.module';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './dashboard/home/home.component';
import { TopnavComponent } from './dashboard/topnav/topnav.component';
import { SidebarComponent } from './dashboard/sidebar/sidebar.component';
import { UploadComponent } from './dashboard/upload/upload.component';

const routes: Routes = [

    {
        path: '', component: LoginComponent
    },
    {
        path: 'dashboard', component: DashboardComponent,
        children: [
            { path: 'home', component: HomeComponent },
            { path: 'upload', component: UploadComponent }
        ]
    },
    {
        path: '**', redirectTo: ''
    }

];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        DashboardComponent,
        HomeComponent,
        TopnavComponent,
        SidebarComponent,
        UploadComponent
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
