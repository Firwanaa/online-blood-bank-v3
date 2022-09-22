package ca.sheridancollege.codeavengers.domain;

import java.util.Set;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Institution extends User{
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private String id;

	// Generated ID to be displayed
	private String userId;
	private String name;
	//private String username;
	//private String Password;
	// email needs validation
	// @Indexed(unique = true)
	//private String email;
	private String city;
	private String code;
	private String postalCode;
	
	
	public Institution(String username, String email, String password, String name, String city, String code,
			String postalCode) {
		super(username, email, password);
		this.name = name;
		this.city = city;
		this.code = code;
		this.postalCode = postalCode;
	}


	@Builder
	public Institution(String id,String username,String email,String password,Set <Role> roles,String userId, String name, String city, String code, String postalCode) {
		super(id, username, email,password,roles);
		this.userId = userId;
		this.name = name;
		this.city = city;
		this.code = code;
		this.postalCode = postalCode;
	}
	
	
	
	
	
}
