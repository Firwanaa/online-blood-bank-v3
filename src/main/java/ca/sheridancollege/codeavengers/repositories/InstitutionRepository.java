package ca.sheridancollege.codeavengers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.codeavengers.domain.Institution;
import ca.sheridancollege.codeavengers.domain.User;

@Repository
public interface InstitutionRepository extends MongoRepository<Institution, String> {
	
	
	Optional<Institution> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);

}
