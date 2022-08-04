package com.studentApp.studentinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studentApp.studentinfo.JWTServices.UserDto;
import com.studentApp.studentinfo.custexceptions.UnAuthorizedException;
import com.studentApp.studentinfo.filter.JwtUtil;

@RestController
public class AuthController {
		
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public String generateToken(@RequestBody UserDto userDto)  throws UnAuthorizedException{
		System.out.println("trrtr");
		try {
			System.out.println("trrtr1");
		 authManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
			System.out.println("trrtr2");

		}
		catch(Exception ex) {
			System.out.println("trrtr3");
			//throw new UnAuthorizedException("Invalid username or password");
			return jwtUtil.generateToken(userDto.getUsername());
		}
		System.out.println("trrtr4");
		return jwtUtil.generateToken(userDto.getUsername());
	}
	
}


