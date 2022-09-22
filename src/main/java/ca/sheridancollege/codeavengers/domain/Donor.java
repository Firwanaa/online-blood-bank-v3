package ca.sheridancollege.codeavengers.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Donor extends User {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private String id;

	// Generated ID to be displayed
	private String userId;
	private String name;
	
	//private String username;
	// email needs validation
	// @Indexed(unique = true)
	//private String email;
	private String city;
	private String address;
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	private String postalCode;
	private Boolean isAvailable;
	private Double lat;
	private  Double lng;
	// password needs validation with security
	// Properties will be userd later
	// private String password;
	

	
	

	/*@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoles(Set<Role> roles) {
		// TODO Auto-generated method stub
		
	}*/
	
	@Builder
	public Donor(String id,String username,String email,String password,Set <Role> roles,String userId, String name, String city, String address, BloodType bloodType, String postalCode,
			Boolean isAvailable, Double lat, Double lng) {
		super(id, username, email,password,roles);
		this.userId = userId;
		this.name = name;
		this.city = city;
		this.address = address;
		this.bloodType = bloodType;
		this.postalCode = postalCode;
		this.isAvailable = isAvailable;
		this.lat = lat;
		this.lng = lng;
	}





	public Donor(String username, String email, String password,String name, String city, String address, BloodType bloodType, String postalCode, Boolean isAvailable,
			Double lat, Double lng) {
		super(username, email,password);
		this.name = name;
		this.city = city;
		this.address = address;
		this.bloodType = bloodType;
		this.postalCode = postalCode;
		this.isAvailable = isAvailable;
		this.lat = lat;
		this.lng = lng;
	}
	
	
	
	

	// private Date lastLoginDate;
	// private Date getLastLoginDateDisplay;
	// private Date joinDate;
	// private String role;
	// private String[] authorities;
	// private boolean isActive;
	// private boolean isNotLocked;
	
}
