package ca.sheridancollege.codeavengers.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.sheridancollege.codeavengers.domain.Role;
import ca.sheridancollege.codeavengers.domain.eRole;

public interface RoleRepository extends MongoRepository<Role, String> {

	 Optional<Role> findByName(eRole name);
}
