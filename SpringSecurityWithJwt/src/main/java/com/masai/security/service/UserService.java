package com.masai.security.service;

import com.masai.exception.UserException;
import com.masai.security.payload.request.SignupRequest;

public interface UserService {

	public String signupUser(SignupRequest signUpRequest) throws UserException;

	public String signupAdmin(SignupRequest signUpRequest) throws UserException;

	public String signupModerator(SignupRequest signUpRequest) throws UserException;

}
