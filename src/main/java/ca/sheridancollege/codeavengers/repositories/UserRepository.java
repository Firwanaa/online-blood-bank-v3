package ca.sheridancollege.codeavengers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAll();

	User findUserByUsername(String username);

	User findUserByEmail(String email);

	List<User> findUserByCity(String city);

	User findUserByPostalCode(String postalCode);

	// List<User> findAllByPostalCode( );

	List<User> findUserByBloodType(BloodType bloodType);
}
