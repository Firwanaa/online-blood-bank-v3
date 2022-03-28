package ca.sheridancollege.codeavengers.service;

import java.util.List;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.User;

public interface UserService {

	User register(String name, String username, String email, String city, String postalcode );
	User save(User user);
	List<User> findAll();
	User findUserByEmail(String email);
	User findUserByUsername(String username);
	List<User> findUserByCity(String city);
	List<User> findUserByBloodType(BloodType bloodType);
	User findUserByPostalCode(String postalcode);
	void sendRequest(String username);
	// List<User> findAllByPostalCode();
	
}
