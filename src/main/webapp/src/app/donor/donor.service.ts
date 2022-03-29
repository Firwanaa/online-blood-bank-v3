import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { BloodType } from '../enumerations/bloodType.enum';
import { Donor } from './donor.model';
@Injectable({
    providedIn: 'root',
})
export class DonorService {
    constructor(private http: HttpClient) {}

    getDonors() {
        return this.http.get<Donor[]>('/api/donor/');
    }
    
    sendEmergencyRequest(){
	return this.http.get('/api/donor/emergencyrequest');
}
    addDonor(donor: Donor) {
        return this.http.post('/api/donor/register', donor);
    }

    findByDonorBloodtype(bloodtype: BloodType) {
        return this.http.get<Donor[]>(
            `/api/donor/findbybloodtype/${bloodtype}`
        );
    }

    findByCity(city: String) {
        return this.http.get<Donor[]>(`/api/donor/findbycity/${city}`);
    }

    onDonorAdded = new EventEmitter<Donor>();
}