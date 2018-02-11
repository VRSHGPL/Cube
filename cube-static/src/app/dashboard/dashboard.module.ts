import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { DashboardComponent } from './dashboard.component';
import { HomeComponent } from './home/home.component';
import { TopnavComponent } from './topnav/topnav.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { UploadComponent } from './upload/upload.component';


@NgModule({
    imports: [RouterModule, FormsModule, CommonModule],
    declarations: [
        DashboardComponent,
        HomeComponent,
        TopnavComponent,
        SidebarComponent,
        UploadComponent
    ],
    exports: [DashboardComponent]

})
export class DashboardModule { }
