package ca.sheridancollege.codeavengers.bootstrap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Donor;
import ca.sheridancollege.codeavengers.domain.Institution;
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
				.city("Mississauga")
				.postalCode("L2S 4FD")
				.build();

		Donor u1 = Donor.builder()
				.name("Alqasasm Firwana")
				.username("Firwanaa123")
				.userId(generateUserId())
				.email("firwanaa@sheridancollege.ca")
				.city("Mississauga")
				.isAvailable(true)
				.postalCode("L3Z 4R2")
				.lat(43.5800932)
				.lng(-79.62516389999999)
				.bloodType(BloodType.ABNeg)
				.build();

		Donor u2 = Donor.builder()
				.name("Doe")
				.username("Doe123")
				.userId(generateUserId())
				.email("Doe123@email.ca")
				.city("Mississauga")
				.isAvailable(true)
				.postalCode("L3A 4R2")
				.lat(43.7035)
				.lng(-79.4409507)
				.bloodType(BloodType.ANeg)
				.build();
		Donor u3 = Donor.builder()
				.name("Jack")
				.username("Jack123")
				.userId(generateUserId())
				.email("jack123@email.ca")
				.city("Mississauga")
				.isAvailable(false)
				.postalCode("L5B 4T2")
				.lat(43.6526235)
				.lng(-79.38815819)
				.bloodType(BloodType.BPos)
				.build();
		Donor u4 = Donor.builder()
				.name("Sally")
				.username("Sally123")
				.userId(generateUserId())
				.email("sally123@email.ca")
				.city("Mississauga")
				.isAvailable(false)
				.postalCode("L3Z 4R2")
				.lat(43.666096399)
				.lng(-79.3873217)
				.bloodType(BloodType.ONeg)
				.build();
		Donor u5 = Donor.builder()
				.name("Sam")
				.username("Sam123")
				.userId(generateUserId())
				.email("sam123@email.ca")
				.city("Mississauga")
				.isAvailable(true)
				.postalCode("L4U 4R2")
				.lat(43.24473479999999)
				.lng(-79.8680303)
				.bloodType(BloodType.ANeg)
				.build();
		Donor u6 = Donor.builder()
				.name("Anna")
				.username("Anna123")
				.userId(generateUserId())
				.email("anna123@email.ca")
				.isAvailable(false)
				.city("Mississauga")
				.postalCode("L3S 3R2")
				.lat(43.71998430000001)
				.lng(-79.6233981)
				.bloodType(BloodType.OPos)
				.build();

		Donor u7 = Donor.builder()
				.name("Ram")
				.username("Ram123")
				.userId(generateUserId())
				.email("ram123@email.ca")
				.isAvailable(true)
				.city("Toronto")
				.postalCode("L7S 3T2")
				.lat(43.6571692)
				.lng(-79.7363508)
				.bloodType(BloodType.APos)
				.build();
		Donor u8 = Donor.builder()
				.name("Xiao")
				.username("Xiao23")
				.userId(generateUserId())
				.email("xiao123@email.ca")
				.isAvailable(true)
				.city("Toronto")
				.postalCode("L9S 2T2")
				.lat(43.7400216)
				.lng(-79.6419648)
				.bloodType(BloodType.ABPos)
				.build();

		Donor u9 = Donor.builder()
				.name("Moe")
				.username("Moe23")
				.userId(generateUserId())
				.email("Moe123@email.ca")
				.isAvailable(false)
				.city("Toronto")
				.postalCode("L9E 2T5")
				.lat(43.7035)
				.lng(-79.4409507)
				.bloodType(BloodType.OPos)
				.build();

		Donor u10 = Donor.builder()
				.name("Romeo")
				.username("Romeo23")
				.userId(generateUserId())
				.email("Romeo123@email.ca")
				.isAvailable(true)
				.city("Brampton")
				.postalCode("L9C 1T5")
				.lat(43.6072671)
				.lng(-79.6498046)
				.bloodType(BloodType.ONeg)
				.build();

		Donor u11 = Donor.builder()
				.name("Lexa")
				.username("Lexa23")
				.userId(generateUserId())
				.email("Lexa123@email.ca")
				.isAvailable(true)
				.city("Brampton")
				.postalCode("L2C 1V5")
				.lat(43.4649338)
				.lng(-79.686222)
				.bloodType(BloodType.BNeg)
				.build();

		Donor u12 = Donor.builder()
				.name("Julia")
				.username("Jolia23")
				.userId(generateUserId())
				.email("Jolia123@email.ca")
				.isAvailable(false)
				.city("Brampton")
				.postalCode("L9C 1T5")
				.lat(443.6454514)
				.lng(-79.3825315)
				.bloodType(BloodType.APos)
				.build();

		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);
		userRepository.save(u4);
		userRepository.save(u5);
		userRepository.save(u6);
		userRepository.save(u7);
		userRepository.save(u8);
		userRepository.save(u9);
		userRepository.save(u10);
		userRepository.save(u11);
		userRepository.save(u12);
		institutionRepository.save(inst1);

	}

	private String generateUserId() {
		return RandomStringUtils.randomNumeric(10);
	}

}
