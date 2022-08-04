package com.studentApp.studentinfo.custexceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleBookNotFoundException(StudentNotFoundException exception, WebRequest request) {
		ExceptionResponse exResponse = new ExceptionResponse(LocalDateTime.now().toString(),exception.getMessage(), HttpStatus.BAD_REQUEST.name(), request.getDescription(false));
		return exResponse;
	}
	
	@ExceptionHandler(TokenIsNotSameException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse handleTokenIsNotSameException(TokenIsNotSameException exception, WebRequest request) {
		ExceptionResponse exResponse = new ExceptionResponse(LocalDateTime.now().toString(),exception.getMessage(), HttpStatus.UNAUTHORIZED.name(), request.getDescription(false));
		return exResponse;
	}
	
	@ExceptionHandler(UnAuthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse handleUnAuthorizedException(UnAuthorizedException exception, WebRequest request) {
		ExceptionResponse exResponse = new ExceptionResponse(LocalDateTime.now().toString(),exception.getMessage(), HttpStatus.UNAUTHORIZED.name(), request.getDescription(false));
		return exResponse;
	}
	
	
}