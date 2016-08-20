package com.senzo.qettal.auth.users;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senzo.qettal.auth.security.LoggedUser;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Users users;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private LoggedUser loggedUser;
	
	@RequestMapping(method = POST)
	public ResponseEntity<String> create(@Valid @RequestBody UserDTO user){
		user
			.toModel(passwordEncoder)
			.save(users);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method = GET)
	public ResponseEntity<UserDTO> get(){
		Optional<User> optionalUser = loggedUser.getUser();
		if(!optionalUser.isPresent()){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		User user = optionalUser.get();
		UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
}
