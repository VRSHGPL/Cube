import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthService } from '../services/auth.service';
import { User } from '../models/user';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    email: string;
    password: string;
    user: User;

    constructor(private authService: AuthService, private router: Router) { }

    ngOnInit() {
        this.user = new User();
    }

    /**
     *  this.router.navigate takes the same parameter array as [routerLink]
     * e.g this.router.navigate(['/heroes']);
     */
    login() {
        this.authService.login(this.user.userName, this.user.password).subscribe(
            data => {
                console.log('Log-ing in User :: ' + data['userName']);
                this.router.navigate(["dashboard/home"]);
            },
            error => {
                console.log(error);
                this.router.navigate(["/"]);
            }
        );
    }

    ping() {
        this.authService.ping().subscribe();
    }
}
