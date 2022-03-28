package ca.sheridancollege.codeavengers.service;

import ca.sheridancollege.codeavengers.domain.Institution;

public interface InstitutionService {
	Institution register(String name, String username, String email, String city, String postalcode);

	Institution save(Institution inst);
}
