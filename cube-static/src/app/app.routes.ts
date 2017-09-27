import { Routes } from '@angular/router';

import { LoginRoutes } from './login/login.routes';
import { LoginComponent } from './login/login.component';

import { DashboardRoutes } from './dashboard/dashboard.routes';

export const routes: Routes = [
     ...LoginRoutes,
     ...DashboardRoutes,
    {
        path: '**',  component: LoginComponent
    }

];
