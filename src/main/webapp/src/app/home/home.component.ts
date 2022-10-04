import {DonorService} from '../donor/donor.service';
import { Component, OnInit } from '@angular/core';
//src/app/donor/donor.service.ts

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;

  constructor(private donorService: DonorService) { }

  ngOnInit(): void {
    this.donorService.getHomePage().subscribe({
      next: data => {
        this.content = data;
      },
      error: err => {
        this.content = JSON.parse(err.error).message;
      }
    });
  }
}
