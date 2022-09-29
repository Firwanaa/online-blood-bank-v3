package ca.sheridancollege.codeavengers.web.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;
import ca.sheridancollege.codeavengers.domain.Institution;
import ca.sheridancollege.codeavengers.domain.Role;
import ca.sheridancollege.codeavengers.domain.eRole;
import ca.sheridancollege.codeavengers.repositories.InstitutionRepository;
import ca.sheridancollege.codeavengers.repositories.RoleRepository;
import ca.sheridancollege.codeavengers.repositories.UserRepository;
import ca.sheridancollege.codeavengers.request.InstitutionSignupRequest;
import ca.sheridancollege.codeavengers.request.LoginRequest;
import ca.sheridancollege.codeavengers.request.SignupRequest;
import ca.sheridancollege.codeavengers.response.JwtResponse;
import ca.sheridancollege.codeavengers.response.MessageResponse;
import ca.sheridancollege.codeavengers.security.JwtUtils;
import ca.sheridancollege.codeavengers.service.impl.InstitutionDetailsImpl;
import ca.sheridancollege.codeavengers.service.impl.UserDetailsImpl;
import ca.sheridancollege.codeavengers.service.impl.UserServiceImpl;

// Test teste test Sep. 15 2022
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/donor")
public class HomeController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	InstitutionRepository institutionRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private Logger LOGGER = LoggerFactory.getLogger(HomeController.class);// or getClass();

	
	@GetMapping("all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Donor> findAllUsers() {
		LOGGER.info("000000********************************************************* ");
		return userServiceImpl.findAll();
	}

	Set<Role> convertStringSetToRoleSetWithStreams(final Set<String> rolesInString) {
		return rolesInString.stream().map(roleInString -> {
			final Role role = roleRepository.findByName(eRole.valueOf(roleInString)).get();
			//role.setName(eRole.valueOf(roleInString));
			//role.setId(null);
			// roleRepository.save(role);
			return role;
		}).collect(Collectors.toSet());
	}

	@PostMapping(value = { "/register" })
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userServiceImpl.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userServiceImpl.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		LOGGER.info("********************************************************* ");

		LOGGER.info("User username: " + signUpRequest.getUsername());
		LOGGER.info("User email: " + signUpRequest.getEmail());
		LOGGER.info("User password: " + signUpRequest.getPassword());
		LOGGER.info("User available: " + signUpRequest.isAvailable());
		LOGGER.info("********************************************************* ");
		LOGGER.info(signUpRequest.getRoles().toString());
		// Create New Donor
		Donor donor = new Donor(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getName(),
				signUpRequest.getCity(),
				signUpRequest.getAddress(),
				signUpRequest.getBloodType(),
				signUpRequest.getPostalCode(),
				signUpRequest.isAvailable(),
				signUpRequest.getLat(),
				signUpRequest.getLng());

		//LOGGER.info("Checkinh123 " + donor.toString());
		LOGGER.info("Checkinh123 " + encoder.encode(signUpRequest.getPassword()));
		
		
		Set<String> strRoles = signUpRequest.getRoles();

		Set<Role> roles = new HashSet<>();

		
		LOGGER.info("User role: " + strRoles);
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(eRole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "ROLE_ADMIN":
						Role adminRole = roleRepository.findByName(eRole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException(
										"Error: Role is not found."));
						roles.add(adminRole);

						break;
					default:
						Role userRole = roleRepository.findByName(eRole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException(
										"Error: Role is not found."));
						roles.add(userRole);
				}
			});

		}

		donor.setRoles(roles);
		userServiceImpl.register(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getCity(),
				signUpRequest.getPostalCode(), signUpRequest.getAddress(), signUpRequest.getBloodType(),
				signUpRequest.isAvailable(),
				signUpRequest.getLat(), signUpRequest.getLng(),
				convertStringSetToRoleSetWithStreams(signUpRequest.getRoles()),
				encoder.encode(signUpRequest.getPassword()));

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

		/*
		 * LOGGER.info("User Register Fucntion invoced");
		 * LOGGER.info("User name: " + donor.getName());
		 * LOGGER.info("User username: " + donor.getUsername());
		 * LOGGER.info("User email: " + donor.getEmail());
		 * LOGGER.info("User city: " + donor.getCity());
		 * LOGGER.info("User postalcode: " + donor.getPostalCode());
		 * LOGGER.info("User isAvailable: " + donor.isAvailable());
		 * LOGGER.info("User BloodType: " + donor.getBloodType());
		 * LOGGER.info("User Lattitude: " + donor.getLat());
		 * LOGGER.info("User Longitude: " + donor.getLng());
		 */

		/*
		 * return userServiceImpl.register(donor.getName(), donor.getUsername(),
		 * donor.getEmail(), donor.getCity(),
		 * donor.getPostalCode(), donor.getAddress(), donor.getBloodType(),
		 * donor.isAvailable(),
		 * donor.getLat(), donor.getLng());
		 */
	}

	@PostMapping(value = { "/registerInstitution" })
	public ResponseEntity<?> registerInstitution(
			@Valid @RequestBody InstitutionSignupRequest institutionSignupRequest) {

		if (institutionRepository.existsByUsername(institutionSignupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (institutionRepository.existsByEmail(institutionSignupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create New Institution
		Institution instituion = new Institution(institutionSignupRequest.getUsername(),
				institutionSignupRequest.getEmail(),
				encoder.encode(institutionSignupRequest.getPassword()),
				institutionSignupRequest.getName(),
				institutionSignupRequest.getCity(),
				institutionSignupRequest.getCode(),
				institutionSignupRequest.getPostalCode());

		Set<String> strRoles = institutionSignupRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(eRole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(eRole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException(
										"Error: Role is not found."));
						roles.add(adminRole);

						break;
					default:
						Role userRole = roleRepository.findByName(eRole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException(
										"Error: Role is not found."));
						roles.add(userRole);
				}
			});

		}

		instituion.setRoles(roles);
		institutionRepository.save(instituion);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		LOGGER.info("User role: " + "********** TESt");
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));
		LOGGER.info("User role: " + "********** TESt3");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		
		
		LOGGER.info("User role: " + userDetails.getId());
		LOGGER.info("User role: " + userDetails.getUsername());
		LOGGER.info("User role: " + userDetails.getEmail());
		LOGGER.info("User role: " + roles.toString());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

	@PostMapping("/signinInstitution")
	public ResponseEntity<?> authenticateInstitution(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		InstitutionDetailsImpl InstitutionDetails = (InstitutionDetailsImpl) authentication.getPrincipal();
		List<String> roles = InstitutionDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				InstitutionDetails.getId(),
				InstitutionDetails.getUsername(),
				InstitutionDetails.getEmail(),
				roles));
	}

	@PostMapping(value = { "/", "" })
	public Donor save(@RequestBody Donor user) {
		user.setId(null);
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
