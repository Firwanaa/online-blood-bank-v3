package ca.sheridancollege.codeavengers.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;
import ca.sheridancollege.codeavengers.domain.Role;
import ca.sheridancollege.codeavengers.domain.User;
import ca.sheridancollege.codeavengers.repositories.UserRepository;
import ca.sheridancollege.codeavengers.service.EmailService;
import ca.sheridancollege.codeavengers.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@Override
	public List<Donor> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Donor save(Donor user) {
		return userRepository.save(user);
	}

	@Override
	public Donor register(String name, String username, String email, String city, String postalcode,
			String address, BloodType bloodType, Boolean isAvailable, Double lat, Double lng,
			Set<Role> role, String password) {
		// TODO we should validate if username or email exists in DB
		Donor user = new Donor();
		// String password = generatePassword();
		String userID = generateUserId();
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setUserId(userID);
		user.setPassword(password);
		user.setAddress(address);
		user.setBloodType(bloodType);
		user.setCity(city);
		user.setPostalCode(postalcode);
		user.setLat(lat);
		user.setLng(lng);
		user.setIsAvailable(isAvailable);
		user.setRoles(role);
		user.setPassword(password);

		/*
		 * try {
		 * emailService.sendNewPasswordEmail(name, password, email);
		 * } catch (Exception e) {
		 * e.printStackTrace();
		 * }
		 */

		// Mongo DB - Set ID to be null, so Mongo DB can generate an ID in the database
		user.setId(null);
		return userRepository.save(user);
	}

	@Override
	public Donor findUserByEmail(String email) {
		// TODO can be used for validation or sending emails
		return null;
	}

	@Override
	public List<Donor> findUserByCity(String city) {
		return userRepository.findByCity(city);
	}

	@Override
	public List<Donor> findUserByBloodType(BloodType bloodType) {
		return userRepository.findUserByBloodType(bloodType);
	}

	@Override
	public List<Donor> findByIsAvailable(boolean isAvailable) {

		return userRepository.findByIsAvailable(isAvailable);
	}

	// @Override
	// public List<User> findAllByPostalCode() {
	// return userRepository.findAllByPostalCode();
	// }

	@Override
	public Donor findUserByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	@Override
	public Donor findUserByPostalCode(String postalcode) {
		return userRepository.findUserByPostalCode(postalcode);
	}

	@Override
	public void sendRequest(String username) {
		Donor user = userRepository.findByUsername(username).get();
		String email = user.getEmail();
		try {

			emailService.sendDonationRequest(username, email);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendEmergencyRequest() {
		try {

			emailService.sendEmergencyRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean existsByUsername(String username) {

		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {

		return userRepository.existsByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

}
