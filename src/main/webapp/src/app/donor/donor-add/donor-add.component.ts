import { Component, OnInit, ViewChild } from '@angular/core';
import { GooglePlaceDirective } from 'ngx-google-places-autocomplete';
import { Address } from 'ngx-google-places-autocomplete/objects/address';
import { Options } from 'ngx-google-places-autocomplete/objects/options/options';
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
    options: Options = {
        types: ['address'],
        componentRestrictions: {
            country: 'CA',
        },
        bounds: undefined,
        fields: ['address_components', 'geometry', 'formatted_address'],
        strictBounds: false,
        origin: undefined,
    };
    formattedAddress = '';
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
    onDonorAdd(event: any) {
        let donor: Donor = new Donor(
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

    @ViewChild('placesRef') placesRef: GooglePlaceDirective;
    public handleAddressChange(address: Address) {
        // Do some stuff
        this.formattedAddress = address.formatted_address;
        // this.address = address.formatted_address;
        // this.city = address.address_components[3].long_name;
        // this.postalCode = address.address_components['postcode'];
        for (var i = 0; i < address.address_components.length; i++) {
            for (
                var j = 0;
                j < address.address_components[i].types.length;
                j++
            ) {
                if (address.address_components[i].types[j] == 'postal_code') {
                    this.postalCode = address.address_components[i].long_name;
                }
            }
        }

        for (var i = 0; i < address.address_components.length; i++) {
            for (
                var j = 0;
                j < address.address_components[i].types.length;
                j++
            ) {
                if (address.address_components[i].types[j] == 'locality') {
                    this.city = address.address_components[i].long_name;
                    console.log("****** City",this.city);
                }
            }
        }

        console.log(address.geometry);
        console.log(address.formatted_address);
        console.log(address.address_components.length);
        console.log(address.address_components);
        // console.log(address.address_components[0]);
        // console.log(address.address_components[1]);
        // console.log(address.address_components[2]);
        // console.log(address.address_components[3]);
        // console.log(address.address_components[4]);
        // console.log(address.address_components[5]);
        // console.log(address.address_components[6]);
        // console.log(address.address_components[7]);
        // console.log(address.address_components[8]);
        // console.log(address.address_components[9]);
        // console.log(address.address_components[10]);
    }
}
