// private Long id;

import { BloodType } from "../enumerations/bloodType.enum";

// // Generated ID to be displayed
// private String userId;
// private String name;
// private String username;
// // email needs validation
// // @Indexed(unique = true)
// private String email;
// private String city;
// @Enumerated(EnumType.STRING)
// private BloodType bloodType;
// private String postalCode;
// private boolean isAvailable;
export class Donor {
    // public id: number;
    public userID: string;
    public name: string;
    public username: string;
    public email: string;
    public city: string;
    public bloodType: BloodType;
    public postalCode: string;
    public address: string;
    public isAvailable: boolean;

    constructor(
        // userID: string,
        name: string,
        username: string,
        email: string,
        city: string,
        postalCode: string,
        address: string,
        bloodType: BloodType,
        isAvailable: boolean
    ) {
        //   this.userID = userID;
        this.name = name;
        this.username = username;
        this.email = email;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
        this.bloodType = bloodType;
        this.isAvailable = isAvailable;
    }
}