package ca.sheridancollege.codeavengers.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.codeavengers.domain.Institution;
import ca.sheridancollege.codeavengers.repositories.InstitutionRepository;
import ca.sheridancollege.codeavengers.service.EmailService;
import ca.sheridancollege.codeavengers.service.InstitutionService;

@Service
public class InstitutionServiceImpl implements InstitutionService,UserDetailsService {


	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private EmailService emailService;

	@Override
	public Institution register(String name, String username, String email, String city, String postalcode) {
		Institution inst = new Institution();
		String password = generatePassword();
		String userID = generateUserId();
		inst.setName(name);
		inst.setEmail(email);
		inst.setUsername(username);
		inst.setCity(city);
		inst.setUserId(userID);
		inst.setPostalCode(postalcode);
		try {

			emailService.sendNewPasswordEmail(name, password, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return institutionRepository.save(inst);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

	@Override
	public Institution save(Institution inst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Institution institution = institutionRepository.findByUsername(username) 
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return null;
	}
}
