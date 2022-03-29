package ca.sheridancollege.codeavengers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;

@Repository
public interface UserRepository extends JpaRepository<Donor, Long> {
	List<Donor> findAll();

	Donor findUserByUsername(String username);

	Donor findUserByEmail(String email);

	List<Donor> findByCity(String city);

	Donor findUserByPostalCode(String postalCode);

	// List<User> findAllByPostalCode( );

	List<Donor> findUserByBloodType(BloodType bloodType);
}
