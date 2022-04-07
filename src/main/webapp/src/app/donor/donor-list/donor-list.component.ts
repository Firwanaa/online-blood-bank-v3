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
    email: String;
    userId: number;
    arrLength: number;

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
                    console.log(d['userID']);
                });
                this.notificationService.notify(
                    'success',
                    donors.length + ' donors loaded successfully'
                );
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
                this.notificationService.notify(
                    'success',
                    donors.length + ' donors loaded successfully'
                );
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
                this.notificationService.notify(
                    'success',
                    donors.length + ' donors loaded successfully'
                );
            },

            (error) => console.log(error)
        );
    }

    SearchAvailability(isAvailable: boolean) {
        this.donorService.findByIsAvailable(isAvailable).subscribe(
            (donors: any[]) => {
                this.donors = donors;
                this.initMap(donors);
                this.notificationService.notify(
                    'success',
                    donors.length + ' donors loaded successfully'
                );
            },
            (error) => console.log(error)
        );
    }

    EmergencyRequest(): void {
        this.donorService.sendEmergencyRequest();
        this.notificationService.notify('error', 'Emergency Declared');
    }

    hospital_icon = {
        url: 'src/assets/hospital.png', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };

    person_icon = {
        url: 'src/assets/person.png', // url
        scaledSize: new google.maps.Size(50, 50), // scaled size
    };
    initMap(donors: Donor[]): void {
        const myLatLng = { lat: 43.6532, lng: -79.3832 };
        const institute = { lat: 43.65602, lng: -79.7387 };
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
                label: d['bloodType'],
                title: 'Username: ' + d['username'],
                icon: this.person_icon,
            });
        });
        new google.maps.Marker({
            position: institute,
            map,
            title: 'institution#1!',
            icon: this.hospital_icon,
        });
    }

    SendRequest(email: String) {
        this.donorService.SendRequest(email).subscribe();
        this.notificationService.notify('success', ' Email sent successfully!');
    }
}
