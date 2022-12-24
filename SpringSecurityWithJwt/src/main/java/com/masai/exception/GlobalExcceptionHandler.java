package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExcceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> myUserExceptionHandler(LoginException e, WebRequest req) {

		ErrorDetails err = new ErrorDetails(e.getMessage(), LocalDateTime.now(), req.getDescription(false));

		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> myUserExceptionHandler(UserException e, WebRequest req) {

		ErrorDetails err = new ErrorDetails(e.getMessage(), LocalDateTime.now(), req.getDescription(false));

		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		ErrorDetails err = new ErrorDetails(me.getBindingResult().getFieldError().getDefaultMessage(),
				LocalDateTime.now(), "Validation Error");
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> myNoHandlerFoundExceptionHandler(NoHandlerFoundException e, WebRequest req) {

		ErrorDetails err = new ErrorDetails(e.getMessage(), LocalDateTime.now(), req.getDescription(false));

		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(Exception e, WebRequest req) {

		ErrorDetails err = new ErrorDetails(e.getMessage(), LocalDateTime.now(), req.getDescription(false));

		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

}
