	// private Long id;

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
  export class Donner {
    // public id: number;
    public userID: string;
    public name: string;
    public username: string;
    public email: string;
    public city: string;
    public bloodType: string;
    public postalCode: string;
    public isAvailable: boolean;

    constructor(userID:string, name:string, username:string, email:string, city:string, BloodType:string, postalCode:string, isAvailable:boolean){
      this.userID = userID;
      this.name = name;
      this.username = username;
      this.email = email;
      this.city = city;
      this.bloodType = BloodType
      this.postalCode = postalCode;
      this.isAvailable = isAvailable;
    }
  }
