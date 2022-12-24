package com.masai.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.masai.exception.UserException;
import com.masai.security.model.ERole;
import com.masai.security.model.User;
import com.masai.security.payload.request.SignupRequest;
import com.masai.security.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public String signupUser(SignupRequest signUpRequest) throws UserException {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserException("Error: Email is already in use!");
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		user.setRole(ERole.ROLE_USER);
		userRepository.save(user);

		return "User registered successfully!";
	}

	@Override
	public String signupAdmin(SignupRequest signUpRequest) throws UserException {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserException("Error: Email is already in use!");
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		user.setRole(ERole.ROLE_USER);
		userRepository.save(user);

		return "Admin registered successfully!";
	}

	@Override
	public String signupModerator(SignupRequest signUpRequest) throws UserException {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new UserException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserException("Error: Email is already in use!");
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		user.setRole(ERole.ROLE_USER);
		userRepository.save(user);

		return "Moderator registered successfully!";
	}

}
