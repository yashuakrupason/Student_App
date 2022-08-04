package com.UserAuth.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UserAuth.security.dto.UserDto;
import com.UserAuth.security.jwt.JwtUtil;

@RestController
public class AuthController {
	
	public static final String TOKEN = "token";
	
	@Autowired
	public AuthenticationManager manager;
			
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	
	@PostMapping("/test")
	public String test() {
	return "test";
	}
	
	@PostMapping("/login")
	public String generateToken(@RequestBody UserDto userDto)  throws Exception{
		System.out.println(userDto.getPassword()+":" +userDto.getUsername());
		try {
		 authManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		}
		catch(Exception ex) {
			throw new Exception("Invalid username or password");
		}
		
		return jwtUtil.generateToken(userDto.getUsername());
	}
	
}


