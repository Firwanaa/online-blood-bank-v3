import { Component, OnInit } from '@angular/core';
import { Donor } from '../donor.model';
import { DonorService } from '../donor.service';
import { BloodType } from 'src/app/enumerations/bloodType.enum';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
	selector: 'app-donor-list',
	templateUrl: './donor-list.component.html',
	styleUrls: ['./donor-list.component.css'],
})
export class DonorListComponent implements OnInit {


	donors: Donor[] = [];
	username: String;
	bloodType: BloodType;
	public bloodTypes = Object.values(BloodType);
	city: String = ''



	constructor(private donorService: DonorService,private notificationService: NotificationService) { }

	ngOnInit() {

		this.donorService.getDonors().subscribe(
			(donors: any[]) => {
				this.donors = donors
                console.log(donors)
                donors.forEach((d) => {
                    console.log(d['available'])
                });



			},
			(error) => console.log(error)
		);

	}
	SearchCity(city: String) {
		this.donorService.findByCity(this.city.toString()).subscribe(
			(donors: any[]) => {
				this.donors = donors
			},
			(error) => console.log(error)

		);
	}
	SearchBloodType(bloodType: BloodType) {
		this.donorService.findByDonorBloodtype(bloodType).subscribe(
			(donors: any[]) => {
				this.donors = donors
			},
			(error) => console.log(error)
		);
	}

	EmergencyRequest():void{
	 this.donorService.sendEmergencyRequest();
	 this.notificationService.notify('error', 'Emergency Declared');
 }
}






