package ca.sheridancollege.codeavengers.request;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ca.sheridancollege.codeavengers.domain.BloodType;
import ca.sheridancollege.codeavengers.domain.Role;

public class SignupRequest {
	
	 @NotBlank
	    @Size(min = 3, max = 20)
	    private String username;
	 
	    private String userId;
		private String name;
	 
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private Set<String> roles;
	    
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;
	    
	  //private String email;
		private String city;
		private String address;
		@Enumerated(EnumType.STRING)
		private BloodType bloodType;
		private String postalCode;
		private Boolean isAvailable;
		private Double lat;
		private  Double lng;
	  
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public String getEmail() {
	        return email;
	    }
	 
	    public void setEmail(String email) {
	        this.email = email;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public Set<String> getRoles() {
	      return this.roles;
	    }
	    
	    public void setRole(Set<String> roles) {
	      this.roles = roles;
	    }

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public Boolean isAvailable() {
			return isAvailable;
		}

		public void setAvailable(Boolean isAvailable) {
			this.isAvailable = isAvailable;
		}

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLng() {
			return lng;
		}

		public void setLng(Double lng) {
			this.lng = lng;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public BloodType getBloodType() {
			return bloodType;
		}

		public void setBloodType(BloodType bloodType) {
			this.bloodType = bloodType;
		}

}
