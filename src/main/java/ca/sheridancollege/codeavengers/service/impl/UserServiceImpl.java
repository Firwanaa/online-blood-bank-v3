package ca.sheridancollege.codeavengers.service.impl;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.User;
import ca.sheridancollege.codeavengers.repositories.UserRepository;
import ca.sheridancollege.codeavengers.service.EmailService;
import ca.sheridancollege.codeavengers.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User register(String name, String username, String email, String city, String postalcode) {
		// TODO we should validate if username or email exists in DB
		User user = new User();
		String password = generatePassword();
		String userID = generateUserId();
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setUserId(userID);
		user.setCity(city);
		user.setPostalCode(postalcode);
		try {
			emailService.sendNewPasswordEmail(name, password, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO can be used for validation or sending emails
		return null;
	}

	@Override
	public List<User> findUserByCity(String city) {
		return userRepository.findUserByCity(city);
	}

	@Override
	public List<User> findUserByBloodType(BloodType bloodType) {
		return userRepository.findUserByBloodType(bloodType);
	}

	// @Override
	// public List<User> findAllByPostalCode() {
	// return userRepository.findAllByPostalCode();
	// }

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	@Override
	public User findUserByPostalCode(String postalcode) {
		return userRepository.findUserByPostalCode(postalcode);
	}

	@Override
	public void sendRequest(String username) {
		User user = userRepository.findUserByUsername(username);
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

}
