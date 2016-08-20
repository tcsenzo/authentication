package com.senzo.qettal.auth.users;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class UserDTO {

	@JsonProperty("auth_id")
	private Long authId;
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;
	@JsonProperty
	private String password;

	/**
	 * @deprecated Jackson eyes only
	 */
	UserDTO() {
	}
	
	
	public UserDTO(Long id, String name, String email) {
		this.authId = id;
		this.name = name;
		this.email = email;
	}

	public User toModel(PasswordEncoder encoder) {
		return new User(name, email, encoder.encode(password));
	}

}