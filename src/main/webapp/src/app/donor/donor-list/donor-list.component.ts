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
    email:String;
    userId:String;
 
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
                    this.initMap(donors);
            },
            (error) => console.log(error)
        );
    }
    SearchCity(city: String) {
        this.donorService.findByCity(this.city.toString()).subscribe(
            (donors: any[]) => {
                this.donors = donors;
                    this.initMap(donors);
            },
            (error) => console.log(error)
        );
    }
    ResetFilter(): void {
        this.donorService.getDonors().subscribe(
            (donors: any[]) => {
                this.donors = donors;
                console.log(donors);
                donors.forEach((d) => {
                    console.log(d['available']);
                    console.log(d['lat']);
                });
                    this.initMap(donors);
            },
            (error) => console.log(error)
        );
    }
    SearchBloodType(bloodType: BloodType) {
        this.donorService.findByDonorBloodtype(bloodType).subscribe(
            (donors: any[]) => {
                this.donors = donors;
                    this.initMap(donors);
            },
            (error) => console.log(error)
        );
    }

    SearchAvailability(isAvailable: boolean) {
        this.donorService.findByIsAvailable(isAvailable).subscribe(
            (donors: any[]) => {
                this.donors = donors;
                    this.initMap(donors);
            },
            (error) => console.log(error)
        );
    }

    EmergencyRequest(): void {
        this.donorService.sendEmergencyRequest();
        this.notificationService.notify('error', 'Emergency Declared');
    }

    initMap(donors: Donor[]): void {
        const myLatLng = { lat: 43.6532, lng: -79.3832 };
        const myLatLng2 = { lat: 43.5800932, lng: -79.62516389999999 };
        const map = new google.maps.Map(
            document.getElementById('map') as HTMLElement,
            {
                zoom: 9,
                center: myLatLng,
            }
        );

        donors.forEach((d) => {
            new google.maps.Marker({
                position: { lat: d['lat'], lng: d['lng'] },
                map,
                title: "Blood Type: "+d['bloodType'],
            });
        });
        new google.maps.Marker({
            position: myLatLng2,
            map,
            title: 'Hello World!',
        });
    }
    
    SendRequest(email: String){
	  this.donorService.SendRequest(email).subscribe(
		
		
	);
 }
}
