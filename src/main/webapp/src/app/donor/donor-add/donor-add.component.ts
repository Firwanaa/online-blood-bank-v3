import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { BloodType } from 'src/app/enumerations/bloodType.enum';
import { NotificationService } from 'src/app/service/notification.service';
import { Donor } from '../donor.model';
import { DonorService } from '../donor.service';

@Component({
    selector: 'app-donor-add',
    templateUrl: './donor-add.component.html',
    styleUrls: ['./donor-add.component.css'],
})
export class DonorAddComponent implements OnInit {
    public bloodTypes = Object.values(BloodType);
    name: string;
    username: string;
    email: string;
    city: string;
    postalCode: string;
    address: string;
    bloodType: BloodType;
    isAvailable: boolean;
    constructor(
        private donorService: DonorService,
        private notificationService: NotificationService
    ) {}

    ngOnInit(): void {}
    onDonorAdd(event: any){
        let donor : Donor = new Donor(
            this.name,
            this.username,
            this.email,
            this.city,
            this.postalCode,
            this.address,
            this.bloodType,
            this.isAvailable
        );
                        console.log(this.name);
                console.log(this.username);
                console.log(this.email);
                console.log(this.city);
                console.log(this.postalCode);
                console.log(this.address);
                console.log(this.bloodType);
                console.log(this.isAvailable);
        if (
            this.name == '' ||
            this.username == '' ||
            this.email == '' ||
            this.city == '' ||
            this.postalCode == '' ||
            this.address == ''

        ) {
            this.notificationService.notify('error', 'No Empty Fields Allowed');
            return;
        } else {
            this.donorService.addDonor(donor).subscribe((newDonor: any) => {
                console.log(this.name);
                console.log(this.username);
                console.log(this.email);
                console.log(this.city);
                console.log(this.postalCode);
                console.log(this.address);
                console.log(this.bloodType);
                console.log(this.isAvailable);
                this.donorService.onDonorAdded.emit(newDonor);
                //empty these variables after adding
                this.name = '';
                this.username = '';
                this.email = '';
                this.city = '';
                this.postalCode = '';
                this.address = '';
                this.bloodType = null;
                this.isAvailable = false;
            });
            this.notificationService.notify(
                'success',
                'New Post added successfully'
            );
        }
    }
}
