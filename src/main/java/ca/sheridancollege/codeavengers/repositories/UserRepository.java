package ca.sheridancollege.codeavengers.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;

@Repository
public interface UserRepository extends MongoRepository<Donor, String> {
	List<Donor> findAll();

	Optional <Donor> findByUsername(String username);

	Donor findUserByEmail(String email);

	List<Donor> findByCity(String city);

	Donor findUserByPostalCode(String postalCode);

	// List<User> findAllByPostalCode( );

	List<Donor> findUserByBloodType(BloodType bloodType);
	
	List<Donor> findByIsAvailable (boolean isAvailable);
	
	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	
}
