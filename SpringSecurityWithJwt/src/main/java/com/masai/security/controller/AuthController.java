package com.masai.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.UserException;
import com.masai.security.jwt.JwtUtils;
import com.masai.security.model.SecurityUser;
import com.masai.security.payload.request.LoginRequest;
import com.masai.security.payload.request.SignupRequest;
import com.masai.security.payload.response.MessageResponse;
import com.masai.security.payload.response.UserInfoResponse;
import com.masai.security.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getUser()));
	}

	@PostMapping("/signup/user")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws UserException {

		String response = userService.signupUser(signUpRequest);

		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/signup/admin")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest) throws UserException {

		String response = userService.signupAdmin(signUpRequest);

		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/signup/moderator")
	public ResponseEntity<?> registerModerator(@Valid @RequestBody SignupRequest signUpRequest) throws UserException {

		String response = userService.signupModerator(signUpRequest);

		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {

		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));

	}
}
