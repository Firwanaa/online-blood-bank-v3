package ca.sheridancollege.codeavengers.bootstrap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Institution;
import ca.sheridancollege.codeavengers.domain.Donor;
import ca.sheridancollege.codeavengers.repositories.InstitutionRepository;
import ca.sheridancollege.codeavengers.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InstitutionRepository institutionRepository;

	@Override
	public void run(String... args) throws Exception {
		Institution inst1 = Institution.builder()
				.userId(generateUserId())
				.name("Blood Institution #1")
				.username("inst1")
				.city("Mississuaga")
				.postalCode("L2S 4FD")
				.build();

		Donor u1 = Donor.builder()
				.name("Alqasasm Firwana")
				.username("Firwanaa123")
				.email("firwanaa@sheridancollege.ca")
				.city("Mississuaga")
				.isAvailable(true)
				.postalCode("L3Z 4R2")
				.bloodType(BloodType.ABNeg)
				.build();

		Donor u2 = Donor.builder()
				.name("Doe")
				.username("Doe123")
				.email("Doe123@email.ca")
				.city("Mississuaga")
				.isAvailable(true)
				.postalCode("L3A 4R2")
				.bloodType(BloodType.ANeg)
				.build();
		Donor u3 = Donor.builder()
				.name("Jack")
				.username("Jack123")
				.email("jack123@email.ca")
				.city("Mississuaga")
				.isAvailable(false)
				.postalCode("L5B 4T2")
				.bloodType(BloodType.BPos)
				.build();
		Donor u4 = Donor.builder()
				.name("Sally")
				.username("Sally123")
				.email("sally123@email.ca")
				.city("Mississuaga")
				.isAvailable(false)
				.postalCode("L3Z 4R2")
				.bloodType(BloodType.ONeg)
				.build();
		Donor u5 = Donor.builder()
				.name("Sam")
				.username("Sam123")
				.email("sam123@email.ca")
				.city("Mississuaga")
				.isAvailable(true)
				.postalCode("L4U 4R2")
				.bloodType(BloodType.ANeg)
				.build();
		Donor u6 = Donor.builder()
				.name("Anna")
				.username("Anna123")
				.email("anna123@email.ca")
				.isAvailable(true)
				.city("Mississuaga")
				.postalCode("L3S 3R2")
				.bloodType(BloodType.OPos)
				.build();
		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);
		userRepository.save(u4);
		userRepository.save(u5);
		userRepository.save(u6);
		institutionRepository.save(inst1);

	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

}
