package ca.sheridancollege.codeavengers.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;
import ca.sheridancollege.codeavengers.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/donor")
public class HomeController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	private Logger LOGGER = LoggerFactory.getLogger(HomeController.class);// or getClass();

	@GetMapping(value = { "/", "" })
	public List<Donor> findAllUsers() {
		return userServiceImpl.findAll();
	}

	@PostMapping(value = { "/register" })
	public Donor register(@RequestBody Donor donor) {
		LOGGER.info("User Register Fucntion invoced");
		LOGGER.info("User name: " + donor.getName());
		LOGGER.info("User username: " + donor.getUsername());
		LOGGER.info("User email: " + donor.getEmail());
		LOGGER.info("User city: " + donor.getCity());
		LOGGER.info("User postalcode: " + donor.getPostalCode());
		LOGGER.info("User isAvailable: " + donor.isAvailable());
		LOGGER.info("User BloodType: " + donor.getBloodType());
		LOGGER.info("User Lattitude: " + donor.getLat());
		LOGGER.info("User Longitude: " + donor.getLng());
		return userServiceImpl.register(donor.getName(), donor.getUsername(), donor.getEmail(), donor.getCity(),
				donor.getPostalCode(), donor.getAddress(), donor.getBloodType(), donor.isAvailable(),
				donor.getLat(), donor.getLng());
	}

	@PostMapping(value = { "/", "" })
	public Donor save(@RequestBody Donor user) {
		return userServiceImpl.save(user);
	}

	@GetMapping("/findbycity/{city}")
	public List<Donor> findByCity(@PathVariable("city") String city) {
		System.out.println("test " + city);
		List<Donor> userList = userServiceImpl.findUserByCity(city);
		System.out.println("work ");
		return userList;
	}

	// not complete
	@GetMapping("/sendrequest/{username}")
	public Donor sendRequest(@PathVariable("username") String username) {
		userServiceImpl.sendRequest(username);
		System.out.println("Username: " + username);
		return null;
	}

	@GetMapping("/emergencyrequest")
	public void sendEmergencyRequest() {
		System.out.println("Emergency Email");
		userServiceImpl.sendEmergencyRequest();
	}

	@GetMapping("/testapi")
	public void testApi() {
		System.out.println("Test Api******************");
	}

	@GetMapping("/findbypostalcode/{postalcode}")
	public Donor findByPostalCode(@PathVariable("postalcode") String postalcode) {
		Donor user = userServiceImpl.findUserByPostalCode(postalcode);
		return user;
	}

	@GetMapping("/findbybloodtype/{bloodtype}")
	public List<Donor> findByBloodType(@PathVariable("bloodtype") String bloodtype) {
		List<Donor> userList = userServiceImpl.findUserByBloodType(BloodType.valueOf(bloodtype));
		return userList;
	}

	@GetMapping("/findByAvailability/{isAvailable}")
	public List<Donor> findByIsAvailable(@PathVariable("isAvailable") boolean isAvailable) {
		List<Donor> userList = userServiceImpl.findByIsAvailable(isAvailable);
		return userList;

	}

}
