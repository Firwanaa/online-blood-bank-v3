import {TokenStorageService} from './_services/token-storage.service';
import {Router} from '@angular/router';
import {DonorService} from './donor/donor.service';
import { Component } from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent   {
    content?: string;
    private roles: string[] = [];
  isLoggedIn = false;
  showDonorList= false;
//   showModeratorBoard = false;
  username?: string;
      constructor(private donorService: DonorService,private tokenStorageService: TokenStorageService, public router: Router) { }

    title = 'Online Blood Bank';
      ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showDonorList = this.roles.includes('ROLE_ADMIN');
    //   this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    // window.location.reload();
this.router.navigateByUrl('')
  .then(() => {
    window.location.reload();
  });
  }
}
