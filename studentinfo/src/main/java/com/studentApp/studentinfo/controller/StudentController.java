package com.studentApp.studentinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.studentApp.studentinfo.custexceptions.StudentNotFoundException;
import com.studentApp.studentinfo.custexceptions.TokenIsNotSameException;
import com.studentApp.studentinfo.services.KafkaSubscriber;
import com.studentApp.studentinfo.services.Student;
import com.studentApp.studentinfo.services.StudentDto;
import com.studentApp.studentinfo.services.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
//	@Autowired
//	KafkaSubscriber kafkaSubsciber;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Student> getStudent(@RequestHeader("Authorization") String token ,@PathVariable long studentId) throws StudentNotFoundException, TokenIsNotSameException {
		System.out.println("Token:"+token);
		//tokenCheck(token);
		return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/student/list")
	public ResponseEntity<List<Student>> getStudentsList() throws TokenIsNotSameException {
		//tokenCheck(token);
		System.out.println("TTT");
		return new ResponseEntity<List<Student>>(studentService.getStudentsList(), HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/student")
	public ResponseEntity<StudentDto> postStudent(@RequestHeader("Authorization") String token ,@RequestBody StudentDto studentDto) throws IllegalArgumentException, TokenIsNotSameException {
		//tokenCheck(token);
		
		StudentDto studentResponse = studentService.addStudent(studentDto);
		return new ResponseEntity<StudentDto>(studentResponse, HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/student")
	public ResponseEntity<StudentDto> updateBook(@RequestHeader("Authorization") String token ,@RequestBody StudentDto studentDto)
			throws IllegalArgumentException,StudentNotFoundException, TokenIsNotSameException {
		//tokenCheck(token);
		return new ResponseEntity<StudentDto>(studentService.updateStudent(studentDto), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Boolean> deleteStudent(@RequestHeader("Authorization") String token ,@PathVariable long studentId) throws IllegalArgumentException,StudentNotFoundException, TokenIsNotSameException {
		//tokenCheck(token);
		studentService.deleteStudent(studentId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
//	public void tokenCheck(String token) throws TokenIsNotSameException {
//		if(!token.equals(kafkaSubsciber.getTokenFromKafkaListener())) {
//			throw new TokenIsNotSameException("Authorisation Error: UnAuthorized");
//		}
//	}

	
}
