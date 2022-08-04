package com.UserAuth.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UserAuth.security.dto.ResponseDto;
import com.UserAuth.security.dto.UserDto;
import com.UserAuth.security.services.SessionRegService;

@RestController
public class AuthController {
	
	public static final String TOKEN = "token";
	
	@Autowired
	public AuthenticationManager manager;
	
	@Autowired
	public SessionRegService sessionRegService;
	
	@Autowired
	KafkaTemplate<String, ResponseDto> kafkaTemplate;
	
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody UserDto user){
		manager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		final String sessionId = sessionRegService.registerSession(user.getUsername());
		
		ResponseDto response = new ResponseDto();
		response.setSessionId(sessionId);
		
		kafkaTemplate.send(TOKEN, response);
		
		System.out.println(response);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@PostMapping("/test")
	public String test() {
	return "test";
	}
	

	
}


