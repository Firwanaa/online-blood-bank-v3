package ca.sheridancollege.codeavengers.service;

import java.util.List;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;

public interface UserService {

	Donor register(String name, String username, String email, String city, String postalcode, String address, BloodType bloodType, Boolean isAvailable);
	Donor save(Donor user);
	List<Donor> findAll();
	Donor findUserByEmail(String email);
	Donor findUserByUsername(String username);
	List<Donor> findUserByCity(String city);
	List<Donor> findUserByBloodType(BloodType bloodType);
	Donor findUserByPostalCode(String postalcode);
	void sendRequest(String username);
	void sendEmergencyRequest();
	// List<User> findAllByPostalCode();
	
}
