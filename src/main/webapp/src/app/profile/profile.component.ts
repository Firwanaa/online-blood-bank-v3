import { Component, OnInit } from '@angular/core';
import { Donor } from '../donor/donor.model';
import { TokenStorageService } from '../_services/token-storage.service';
import { DonorService } from '../donor/donor.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  donor: Donor;
  username: String;
  city: String;
  postalCode: String;


  constructor(private token: TokenStorageService, private donorService: DonorService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.username = this.currentUser.username;
    this.donorService.findByUsername(this.username.toString()).subscribe(
      (donor: any) => {
        console.log(donor);
         this.donor = donor;
        //this.city = donor.city;
        //this.postalCode = donor.postalCode;
        console.log(donor.city);
        }, 
         (error) => console.log(error)); 
        }
  }

  // FindUser(username: String) { 
  //   this.donorService.findByUsername(this.username.toString()).subscribe(
  //     (donor: any) => {
  //       console.log(donor);
  //        this.donor = donor;
  //       this.city = donor.city;
  //       this.postalCode = donor.postalCode;
  //       console.log(donor.city);
  //       }, 
  //        (error) => console.log(error)); 
  //       }

