import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Donor } from './donor.model';
import {BloodType} from '../enum/bloodType.enum';
@Injectable({
    providedIn: 'root',
})
export class DonorService {
    constructor(private http: HttpClient) {}

    getDonors() {
        return this.http.get<Donor[]>('/api/donor');
    }

    addDonor(donor: Donor) {
        return this.http.post('/api/donor', donor);
    }

    findByDonorBloodtype(bloodtype: BloodType) {
        return this.http.get<Donor[]>(
            `/api/donor/findbybloodtype/${bloodtype}`
        );
    }

    findByCity(username: String) {
        return this.http.get<Donor[]>(`/api/donor/findbycity/${username}`);
    }
}
