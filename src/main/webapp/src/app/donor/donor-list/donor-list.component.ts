import { Component, OnInit } from '@angular/core';
import { BloodType } from 'src/app/enumerations/bloodType.enum';
import { NotificationService } from 'src/app/service/notification.service';
import { Donor } from '../donor.model';
import { DonorService } from '../donor.service';
@Component({
    selector: 'app-donor-list',
    templateUrl: './donor-list.component.html',
    styleUrls: ['./donor-list.component.css'],
})
export class DonorListComponent implements OnInit {
    donors: Donor[] = [];
    username: String;
    bloodType: BloodType;
    isAvailable: boolean;
    public bloodTypes = Object.values(BloodType);
    city: String = '';

    constructor(
        private donorService: DonorService,
        private notificationService: NotificationService
    ) {}

    ngOnInit() {
        this.donorService.getDonors().subscribe(
            (donors: any[]) => {
                this.donors = donors;
                console.log(donors);
                donors.forEach((d) => {
                    console.log(d['available']);
                });

            },
            (error) => console.log(error)
        );
        this.initMap();
    }
    SearchCity(city: String) {
        this.donorService.findByCity(this.city.toString()).subscribe(
            (donors: any[]) => {
                this.donors = donors;
            },
            (error) => console.log(error)
        );
    }
    SearchBloodType(bloodType: BloodType) {
        this.donorService.findByDonorBloodtype(bloodType).subscribe(
            (donors: any[]) => {
                this.donors = donors;
            },
            (error) => console.log(error)
        );
    }

    SearchAvailability(isAvailable: boolean) {
        this.donorService.findByIsAvailable(isAvailable).subscribe(
            (donors: any[]) => {
                this.donors = donors;
            },
            (error) => console.log(error)
        );
    }

    EmergencyRequest(): void {
        this.donorService.sendEmergencyRequest();
        this.notificationService.notify('error', 'Emergency Declared');
    }

 initMap(): void {
  const myLatLng = { lat: 43.6532, lng: -79.3832};

  const map = new google.maps.Map(
    document.getElementById("map") as HTMLElement,
    {
      zoom: 9,
      center: myLatLng,
    }
  );

  new google.maps.Marker({
    position: myLatLng,
    map,
    title: "Hello World!",
  });
=======



			},
			(error) => console.log(error)
		);

	}
	SearchCity(city: String) {
		this.donorService.findByCity(this.city.toString()).subscribe(
			(donors: any[]) => {
				this.donors = donors
				this.city='';
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
	
	SearchAvailability(isAvailable: boolean){
		this.donorService.findByIsAvailable(isAvailable).subscribe(
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
 
  ResetFilter():void{
	
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

 

}

}
