package ca.sheridancollege.codeavengers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.codeavengers.domain.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
