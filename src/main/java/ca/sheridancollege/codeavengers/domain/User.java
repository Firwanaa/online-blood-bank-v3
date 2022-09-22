package ca.sheridancollege.codeavengers.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
	


	@Id 
	String id ="";

	 
	  @NotBlank
	  @Size(max = 20) 
	  String username="";

	  @NotBlank
	  @Size(max = 50)
	  @Email 
	  String email="";

	  @NotBlank
	  @Size(max = 120) 
	  String password="";

	  @DBRef 
	  Set<Role> roles = new HashSet<>();

	  	 public User(String username, String email, String password) {
		// TODO Auto-generated constructor stub
	}

	/*String getId();
	void setId(String id);
	
	String getUsername();
	void setUsername(String username);
	
	String getEmail();
	void setEmail(String email);
	
	String getPassword();
	void setPassword(String password);
	
	Set<Role> getRoles();
	void setRoles(Set<Role> roles);*/
	
}
