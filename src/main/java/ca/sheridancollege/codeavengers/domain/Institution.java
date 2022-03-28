package ca.sheridancollege.codeavengers.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institution {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Generated ID to be displayed
	private String userId;
	private String name;
	private String username;
	private String Password;
	// email needs validation
	// @Indexed(unique = true)
	private String email;
	private String city;
	private String code;
	private String postalCode;
}
