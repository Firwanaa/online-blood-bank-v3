package ca.sheridancollege.codeavengers.service;

import java.util.List;
import java.util.Set;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;
import ca.sheridancollege.codeavengers.domain.Role;

public interface UserService {

	Donor register(String name, String username, String email, String city, String postalcode, String address, BloodType bloodType, Boolean isAvailable, Double lat, Double lng, Set <Role> role,String password);
	Donor save(Donor user);
	List<Donor> findAll();
	Donor findUserByEmail(String email);
	Donor findUserByUsername(String username);
	List<Donor> findUserByCity(String city);
	List<Donor> findUserByBloodType(BloodType bloodType);
	Donor findUserByPostalCode(String postalcode);
	void sendRequest(String username);
	void sendEmergencyRequest();
	List<Donor> findByIsAvailable (boolean isAvailable);
	Boolean existsByUsername(String username);
	 Boolean existsByEmail(String email);
	// List<User> findAllByPostalCode();
	
}
