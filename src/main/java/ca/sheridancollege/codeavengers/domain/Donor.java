package ca.sheridancollege.codeavengers.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Generated ID to be displayed
	private String userId;
	private String name;
	private String username;
	// email needs validation
	// @Indexed(unique = true)
	private String email;
	private String city;
	private String address;
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	private String postalCode;
	private boolean isAvailable;
	private Double lat;
	private  Double lng;
	// password needs validation with security
	// Properties will be userd later
	// private String password;
	// private Date lastLoginDate;
	// private Date getLastLoginDateDisplay;
	// private Date joinDate;
	// private String role;
	// private String[] authorities;
	// private boolean isActive;
	// private boolean isNotLocked;
}
