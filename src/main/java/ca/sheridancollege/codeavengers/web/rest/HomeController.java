package ca.sheridancollege.codeavengers.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.User;
import ca.sheridancollege.codeavengers.service.impl.InstitutionServiceImpl;
import ca.sheridancollege.codeavengers.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = { "/", "/user" })
public class HomeController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/", "" })
	public List<User> findAllUsers() {
		return userServiceImpl.findAll();
	}

	@PostMapping(value = { "/", "" })
	public User save(@RequestBody User user) {
		return userServiceImpl.save(user);
	}

	@GetMapping("/findbycity/{city}")
	public List<User> findByUsername(@PathVariable("city") String city) {
		List<User> userList = userServiceImpl.findUserByCity(city);
		return userList;
	}

	//not complete
	@GetMapping("/sendRequest/{cit}")
	public List<User> sendRequest(@PathVariable("city") String city) {
		List<User> userList = userServiceImpl.findUserByCity(city);
		return userList;
	}

	@GetMapping("/findbypostalcode/{postalcode}")
	public User findByPostalCode(@PathVariable("postalcode") String postalcode) {
		User user = userServiceImpl.findUserByPostalCode(postalcode); 
		return user;
	}

	@GetMapping("/findbybloodtype/{bloodtype}")
	public List<User> findByBloodType(@PathVariable("bloodtype") String bloodtype) {
		List<User> userList = userServiceImpl.findUserByBloodType(BloodType.valueOf(bloodtype));
		return userList;
	}
}
