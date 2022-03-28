import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { BloodType } from 'src/app/enum/bloodType.enum';
import { DonorService } from '../donor.service';

@Component({
    selector: 'app-donor-add',
    templateUrl: './donor-add.component.html',
    styleUrls: ['./donor-add.component.css'],
})
export class DonorAddComponent implements OnInit {
    name: string;
    username: string;
    email: string;
    city: string;
    postalCode: string;
    address: string;
    bloodType: BloodType;
    constructor(
        private donorService: DonorService,
        private notifiedService: NotifierService
    ) {}

    ngOnInit(): void {}
}
